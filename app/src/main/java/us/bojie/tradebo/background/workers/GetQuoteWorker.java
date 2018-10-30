package us.bojie.tradebo.background.workers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import okhttp3.ResponseBody;
import retrofit2.Response;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.bean.response.Quote;
import us.bojie.tradebo.di.component.DaggerWorkerCommponent;
import us.bojie.tradebo.utils.Constants;

public class GetQuoteWorker extends Worker {

    private static final String TAG = GetQuoteWorker.class.getSimpleName();
    private final ApiService apiService;

    public GetQuoteWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        apiService = DaggerWorkerCommponent.create().getWebService();
    }

    @NonNull
    @Override
    public Result doWork() {
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
                    setOutputData(new Data.Builder()
                            .putString(Constants.KEY_ASK_PRICE,askPrice).build());
//                    Toast.makeText(getApplicationContext(), askPrice, Toast.LENGTH_SHORT).show();
//                    WorkUtils.makeStatusNotification(askPrice, getApplicationContext());
                    saveInFirebase(askPrice);
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

    private void saveInFirebase(String askPrice) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String,String> map = new HashMap<>();
        map.put("time", Timestamp.now().toDate().toString());
        map.put("askPrice", askPrice);
        db.collection("test").add(map);
    }
}
