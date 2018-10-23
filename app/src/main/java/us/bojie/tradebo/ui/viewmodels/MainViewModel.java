package us.bojie.tradebo.ui.viewmodels;


import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.bean.Quote;
import us.bojie.tradebo.database.entity.Instrument;
import us.bojie.tradebo.database.entity.OwnedStock;
import us.bojie.tradebo.database.entity.Token;
import us.bojie.tradebo.repositories.InstrumentRepository;
import us.bojie.tradebo.repositories.OwnedStockRepository;
import us.bojie.tradebo.repositories.TokenRepository;

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<Token> token;
    private final TokenRepository tokenRepository;
    private final OwnedStockRepository ownedStockRepository;
    private final InstrumentRepository instrumentRepository;
    private final ApiService webservice;

    @Inject
    public MainViewModel(TokenRepository tokenRepository, OwnedStockRepository ownedStockRepository,
                         InstrumentRepository instrumentRepository, ApiService webservice) {
        this.tokenRepository = tokenRepository;
        this.ownedStockRepository = ownedStockRepository;
        this.instrumentRepository = instrumentRepository;
        this.webservice = webservice;
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

    public LiveData<Quote> getQuote(String symbol) {
        MutableLiveData<Quote> res = new MutableLiveData<>();
        webservice.getQuoteFromSymbol(symbol).enqueue(new Callback<Quote>() {
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
}
