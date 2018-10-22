package us.bojie.tradebo.repositories;

import android.util.Log;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.bean.CommonResponse;
import us.bojie.tradebo.database.dao.OwnedStockDao;
import us.bojie.tradebo.database.entity.OwnedStock;

public class OwnedStockRepository {

    public static final String TAG = OwnedStockRepository.class.getSimpleName();
    private MediatorLiveData<List<OwnedStock>> ownedStocksLive = new MediatorLiveData<>();

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
        final LiveData<List<OwnedStock>> ownedStocks = ownedStockDao.load();

        https://stackoverflow.com/a/44471378/4186942
        ownedStocksLive.addSource(ownedStocks, ownedStockList -> {
            if (ownedStockList == null || ownedStockList.isEmpty()) {
                refreshOwnedStockList(tokenString);
            } else {
                ownedStocksLive.removeSource(ownedStocks);
                ownedStocksLive.setValue(ownedStockList);
            }
        });
        return ownedStocksLive;
    }

    private void refreshOwnedStockList(String tokenString) {
        executor.execute(() -> webservice.getUserOwnedStock(tokenString).enqueue(new Callback<CommonResponse<List<OwnedStock>>>() {
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
        }));
    }
}