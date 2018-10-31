package us.bojie.tradebo.background.workers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.bean.request.OrderRequest;
import us.bojie.tradebo.bean.response.Quote;
import us.bojie.tradebo.database.entity.Order;
import us.bojie.tradebo.di.component.DaggerWorkerComponent;
import us.bojie.tradebo.utils.Constants;
import us.bojie.tradebo.utils.WorkUtils;

public class RealWorker extends Worker {

    private static final String TAG = RealWorker.class.getSimpleName();
    @Inject
    ApiService apiService;
//    @Inject
//    OrderRepository orderRepository;
//    OwnedStockRepository ownedStockRepository;
//    private final InstrumentRepository instrumentRepository;

    public RealWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        DaggerWorkerComponent.create().inject(this);
    }

    @NonNull
    @Override
    public Result doWork() {
        if (!WorkUtils.isInOpenStockMarketTime()) {
            return Result.SUCCESS;
        }
        String symbol = null;
        try {
            Data args = getInputData();
            symbol = args.getString(Constants.KEY_SYMBOL);
            Response<Quote> response = apiService.getQuoteFromSymbol(symbol).execute();
            if (!response.isSuccessful()) {
                ResponseBody errorBody = response.errorBody();
                String error = errorBody != null ? errorBody.string() : null;
                String message = String.format("Request failed %s (%s)", symbol, error);
                Log.e(TAG, message);
//                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                return Result.FAILURE;
            } else {
                Quote quote = response.body();
                if (quote != null) {
                    String askPrice = quote.getAskPrice();
                    String dateString = Timestamp.now().toDate().toString();
                    setOutputData(new Data.Builder()
                            .putString(Constants.KEY_TIME, dateString)
                            .build());
//                    Toast.makeText(getApplicationContext(), askPrice, Toast.LENGTH_SHORT).show();
//                    WorkUtils.makeStatusNotification(askPrice, getApplicationContext());
                    saveInFirebase(askPrice, dateString);
                }
                return Result.SUCCESS;
            }
        } catch (Exception e) {
            String message = String.format("Failed to get quote with symbol %s", symbol);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            Log.e(TAG, message);
            return Result.FAILURE;
        }
    }

    private void saveInFirebase(String askPrice, String dateString) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("time", dateString);
        map.put("askPrice", askPrice);
        db.collection("askPrice").add(map);
    }

    private LiveData<Quote> getQuote(String symbol) {
        MutableLiveData<Quote> res = new MutableLiveData<>();
        apiService.getQuoteFromSymbol(symbol).enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                Quote quote = response.body();
                res.setValue(quote);
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
        return res;
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
