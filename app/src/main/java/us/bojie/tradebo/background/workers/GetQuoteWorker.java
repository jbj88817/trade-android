package us.bojie.tradebo.background.workers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import okhttp3.ResponseBody;
import retrofit2.Response;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.bean.Quote;
import us.bojie.tradebo.utils.Constants;

public class GetQuoteWorker extends Worker {

    private static final String TAG = GetQuoteWorker.class.getSimpleName();
    private final ApiService apiService;

    @Inject
    public GetQuoteWorker(@NonNull Context context, @NonNull WorkerParameters workerParams,
                          ApiService apiService) {
        super(context, workerParams);
        this.apiService = apiService;
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
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                return Result.FAILURE;
            } else {
                Quote quote = response.body();
                if (quote != null) {
                    String askPrice = quote.getAskPrice();
                    setOutputData(new Data.Builder()
                            .putString(Constants.KEY_ASK_PRICE,askPrice).build());
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
}
