package us.bojie.tradebo.background.workers;

import android.content.Context;
import android.util.Log;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.bean.request.OrderRequest;
import us.bojie.tradebo.bean.response.Quote;
import us.bojie.tradebo.database.entity.Order;
import us.bojie.tradebo.di.component.DaggerWorkerComponent;
import us.bojie.tradebo.utils.Constants;
import us.bojie.tradebo.utils.StringUtils;

public class RealWorker extends Worker {

    private static final String TAG = RealWorker.class.getSimpleName();
    @Inject
    ApiService apiService;
    private FirebaseFirestore db;
    private String token;
//    @Inject
//    OrderRepository orderRepository;
//    OwnedStockRepository ownedStockRepository;
//    private final InstrumentRepository instrumentRepository;

    public RealWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        DaggerWorkerComponent.create().inject(this);
        db = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public Result doWork() {
//        if (!WorkUtils.isInOpenStockMarketTime()) {
//            return Result.SUCCESS;
//        }
        token = getInputData().getString(Constants.KEY_TOKEN);
        fetchSymbolListFromFirebase();
        return Result.SUCCESS;
//        try {
//            Response<Quote> response = apiService.getQuoteFromSymbol(symbol).execute();
//            if (!response.isSuccessful()) {
//                ResponseBody errorBody = response.errorBody();
//                String error = errorBody != null ? errorBody.string() : null;
//                String message = String.format("Request failed %s (%s)", symbol, error);
//                Log.e(TAG, message);
//                return Result.FAILURE;
//            } else {
//                Quote quote = response.body();
//                if (quote != null) {
//                    String askPrice = quote.getAskPrice();
//                    String dateString = Timestamp.now().toDate().toString();
//                    setOutputData(new Data.Builder()
//                            .putString(Constants.KEY_TIME, dateString)
//                            .build());
////                    WorkUtils.makeStatusNotification(askPrice, getApplicationContext());
//                    saveQuoteInFirebase(askPrice, dateString);
//                }
//                return Result.SUCCESS;
//            }
//        } catch (Exception e) {
//            String message = String.format("Failed to get quote with symbol %s", symbol);
//            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//            Log.e(TAG, message);
//            return Result.FAILURE;
//        }
    }

    private void fetchSymbolListFromFirebase() {
        db.collection(Constants.KEY_INSTRUMENTS)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String symbol = document.getId();
                            String instrumentUrl = (String) document.getData().get(Constants.KEY_URL);
                            String avgPrice = (String) document.getData().get(Constants.KEY_AVG_PRICE);
                            getQuote(symbol, avgPrice, instrumentUrl);
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });

    }

    private void saveQuoteInFirebase(String askPrice, String symbol, String avgPrice) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.KEY_TIME, Timestamp.now().toDate().toString());
        map.put(Constants.KEY_ASK_PRICE, askPrice);
        map.put(Constants.KEY_AVG_PRICE, avgPrice);
        db.collection(Constants.KEY_QUOTE).document(symbol).set(map);
    }

    private void getQuote(String symbol, String avgPrice, String instrumentUrl) {
        apiService.getQuoteFromSymbol(symbol).enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                Quote quote = response.body();
                if (quote != null) {
                    saveQuoteInFirebase(quote.getAskPrice(), quote.getSymbol(), avgPrice);
                    comparePriceAndCallPlaceOrder(quote.getAskPrice(), avgPrice, symbol, instrumentUrl);
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void comparePriceAndCallPlaceOrder(String askPriceString, String avgPriceString,
                                               String symbol, String instrumentUrl) {
        Double askPrice = Double.valueOf(askPriceString);
        Double avgPrice = Double.valueOf(avgPriceString);
        fetchOrderFromFirebase(instrumentUrl, askPrice, avgPrice, symbol);
    }

    private void fetchOrderFromFirebase(String instrumentUrl, Double askPrice,
                                        Double avgPrice, String symbol) {
        DocumentReference docRef = db.collection(Constants.KEY_ORDER)
                .document(StringUtils.getInstrumentIdFromUrl(instrumentUrl));
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document != null && document.exists()) {
                    if (askPrice > avgPrice * 1.10) {
                        double sellingPrice = avgPrice * 1.08;
                        String formatSellPrice = String.format(Locale.US, "%.2f", sellingPrice);
                        OrderRequest request = new OrderRequest.Builder(instrumentUrl, symbol,
                                "sell", "9", formatSellPrice).build();
                        placeOrder(token, request);
                    }
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });
    }

    private void placeOrder(String tokenString, OrderRequest orderRequest) {
        apiService.postOrder(tokenString, orderRequest).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Order order = response.body();
                if (order != null) {
                    saveOrderInFirebase(order);
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void saveOrderInFirebase(Order order) {
        db.collection(Constants.KEY_ORDER).document(StringUtils.getInstrumentIdFromUrl(order
                .getInstrument())).set(order);
    }
}
