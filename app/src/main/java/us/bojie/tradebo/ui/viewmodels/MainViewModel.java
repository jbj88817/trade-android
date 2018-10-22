package us.bojie.tradebo.ui.viewmodels;


import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import us.bojie.tradebo.database.entity.Instrument;
import us.bojie.tradebo.database.entity.OwnedStock;
import us.bojie.tradebo.database.entity.Token;
import us.bojie.tradebo.repositories.InstrumentRepository;
import us.bojie.tradebo.repositories.OwnedStockRepository;
import us.bojie.tradebo.repositories.TokenRepository;

public class MainViewModel extends ViewModel {
    private LiveData<Token> token;
    private LiveData<List<OwnedStock>> ownedStocksList;
    private TokenRepository tokenRepository;
    private OwnedStockRepository ownedStockRepository;
    private InstrumentRepository instrumentRepository;

    @Inject
    public MainViewModel(TokenRepository tokenRepository, OwnedStockRepository ownedStockRepository,
                         InstrumentRepository instrumentRepository) {
        this.tokenRepository = tokenRepository;
        this.ownedStockRepository = ownedStockRepository;
        this.instrumentRepository = instrumentRepository;
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
}
