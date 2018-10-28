package us.bojie.tradebo.repositories;

import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.bojie.tradebo.App;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.bean.request.OauthRequest;
import us.bojie.tradebo.database.dao.TokenDao;
import us.bojie.tradebo.database.entity.Token;

public class TokenRepository {

    public static final String TAG = TokenRepository.class.getSimpleName();

    private final ApiService webservice;
    private final TokenDao tokenDao;
    private final Executor executor;

    @Inject
    public TokenRepository(ApiService webservice, TokenDao tokenDao, Executor executor) {
        this.webservice = webservice;
        this.tokenDao = tokenDao;
        this.executor = executor;
    }

    // ---

    public LiveData<Token> getToken() {
        refreshToken(); // try to refresh data if possible from Api
        return tokenDao.load(); // return a LiveData directly from the database.
    }

    // ---

    private void refreshToken() {
        executor.execute(() -> {
            // Check if token was fetched recently
            boolean tokenExists = (tokenDao.hasToken(getMaxRefreshTime(new Date())) != null);
            // If token have to be updated
            if (!tokenExists) {
                webservice.getToken(
                        new OauthRequest.Builder().build())
                        .enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(@NonNull Call<Token> call, @NonNull Response<Token> response) {
                        Log.e(TAG, "DATA REFRESHED FROM NETWORK");
                        Toast.makeText(App.context, "Data refreshed from network !", Toast.LENGTH_LONG).show();
                        executor.execute(() -> {
                            Token token = response.body();
                            if (token != null) {
                                token.setLastRefresh(new Date());
                            }
                            tokenDao.save(token);
                        });
                    }

                    @Override
                    public void onFailure(@NonNull Call<Token> call, @NonNull Throwable t) {
                        Log.e(TAG, t.getMessage());
                    }
                });
            }
        });
    }

    // ---

    private Date getMaxRefreshTime(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        int FRESH_TIMEOUT_IN_SECONDS = 86400;
        cal.add(Calendar.SECOND, -FRESH_TIMEOUT_IN_SECONDS);
        return cal.getTime();
    }
}
