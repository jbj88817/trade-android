package us.bojie.tradebo.background.workers;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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

public class RealWorker extends Worker {

    private static final String TAG = RealWorker.class.getSimpleName();
    @Inject
    ApiService apiService;
    private FirebaseFirestore db;
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
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String symbol = (String) document.getData().get(Constants.KEY_SYMBOL);
                                getQuote(symbol);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    private void saveQuoteInFirebase(String askPrice, String symbol) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.KEY_TIME, Timestamp.now().toDate().toString());
        map.put(Constants.KEY_ASK_PRICE, askPrice);
        map.put(Constants.KEY_SYMBOL, symbol);
        db.collection("askPrice").add(map);
    }

    private void getQuote(String symbol) {
        apiService.getQuoteFromSymbol(symbol).enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                Quote quote = response.body();
                if (quote != null) {
                    saveQuoteInFirebase(quote.getAskPrice(), quote.getSymbol());
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private LiveData<Order> placeOrder(String tokenString, OrderRequest orderRequest) {
        MutableLiveData<Order> orderMutableLiveData = new MutableLiveData<>();
        apiService.postOrder(tokenString, orderRequest).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Order order = response.body();
//                orderRepository.saveOrder(order);
                orderMutableLiveData.setValue(order);
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
        return orderMutableLiveData;
    }
}
