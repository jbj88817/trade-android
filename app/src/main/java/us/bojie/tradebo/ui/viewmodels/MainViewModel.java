package us.bojie.tradebo.ui.viewmodels;


import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import us.bojie.tradebo.background.workers.RealWorker;
import us.bojie.tradebo.database.entity.Instrument;
import us.bojie.tradebo.database.entity.OwnedStock;
import us.bojie.tradebo.database.entity.Token;
import us.bojie.tradebo.repositories.InstrumentRepository;
import us.bojie.tradebo.repositories.OwnedStockRepository;
import us.bojie.tradebo.repositories.TokenRepository;
import us.bojie.tradebo.utils.Constants;

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<Token> token;
    private final TokenRepository tokenRepository;
    private final OwnedStockRepository ownedStockRepository;
    private final InstrumentRepository instrumentRepository;
    private final WorkManager workManager;

    @Inject
    public MainViewModel(TokenRepository tokenRepository, OwnedStockRepository ownedStockRepository,
                         InstrumentRepository instrumentRepository) {
        this.tokenRepository = tokenRepository;
        this.ownedStockRepository = ownedStockRepository;
        this.instrumentRepository = instrumentRepository;
        this.workManager = WorkManager.getInstance();
    }

    public void init() {
        if (this.token != null) {
            return;
        }
        token = tokenRepository.getToken();
    }

    public LiveData<Token> getToken() {
        return this.token;
    }

    public LiveData<List<OwnedStock>> getOwnedStocksList(String tokenString, boolean isRefreshing) {
        return ownedStockRepository.getOwnedStockList(tokenString, isRefreshing);
    }

    public LiveData<Instrument> getInstrument(String instrumentId) {
        return instrumentRepository.getInstrument(instrumentId);
    }

    public void startService(String tokenString) {

        Log.d(TAG, "!!!Service started !!!");
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(RealWorker.class,
                15, TimeUnit.MINUTES)
                .setInputData(new Data.Builder().putString(Constants.KEY_TOKEN, tokenString).build())
                .addTag(Constants.TAG_GET_QUOTE)
                .build();

        workManager.enqueueUniquePeriodicWork("my_unique_worker",
                ExistingPeriodicWorkPolicy.KEEP, workRequest);
    }

    public void stopService() {
        workManager.cancelAllWorkByTag(Constants.TAG_GET_QUOTE);
    }
}
