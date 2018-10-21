package us.bojie.tradebo.repositories;

import android.util.Log;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.bean.CommonResponse;
import us.bojie.tradebo.database.dao.OwnedStockDao;
import us.bojie.tradebo.database.entity.OwnedStock;

public class OwnedStockRepository {

    public static final String TAG = OwnedStockRepository.class.getSimpleName();

    private final ApiService webservice;
    private final OwnedStockDao ownedStockDao;
    private final Executor executor;

    @Inject
    public OwnedStockRepository(ApiService webservice, OwnedStockDao ownedStockDao, Executor executor) {
        this.webservice = webservice;
        this.ownedStockDao = ownedStockDao;
        this.executor = executor;
    }

    // ---

    public LiveData<List<OwnedStock>> getOwnedStockList(String tokenString) {
        LiveData<List<OwnedStock>> res = ownedStockDao.load();
        if (res.getValue() == null) {
            refreshOwnedStockList(tokenString);
        }
        return res;
    }

    private void refreshOwnedStockList(String tokenString) {
        executor.execute(() -> {
            webservice.getUserOwnedStock(tokenString).enqueue(new Callback<CommonResponse<List<OwnedStock>>>() {
                @Override
                public void onResponse(Call<CommonResponse<List<OwnedStock>>> call, Response<CommonResponse<List<OwnedStock>>> response) {
                    executor.execute(() -> {
                        CommonResponse<List<OwnedStock>> body = response.body();
                        List<OwnedStock> results = null;
                        if (body != null) {
                            results = body.getResults();
                        }
                        ownedStockDao.save(results);
                    });
                }

                @Override
                public void onFailure(Call<CommonResponse<List<OwnedStock>>> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
        });
    }
}