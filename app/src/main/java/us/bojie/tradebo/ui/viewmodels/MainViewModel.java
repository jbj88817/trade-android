package us.bojie.tradebo.ui.viewmodels;


import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.Data;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.background.workers.GetQuoteWorker;
import us.bojie.tradebo.bean.request.OrderRequest;
import us.bojie.tradebo.bean.response.Quote;
import us.bojie.tradebo.database.entity.Instrument;
import us.bojie.tradebo.database.entity.Order;
import us.bojie.tradebo.database.entity.OwnedStock;
import us.bojie.tradebo.database.entity.Token;
import us.bojie.tradebo.repositories.InstrumentRepository;
import us.bojie.tradebo.repositories.OrderRepository;
import us.bojie.tradebo.repositories.OwnedStockRepository;
import us.bojie.tradebo.repositories.TokenRepository;
import us.bojie.tradebo.utils.Constants;

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<Token> token;
    private final TokenRepository tokenRepository;
    private final OwnedStockRepository ownedStockRepository;
    private final InstrumentRepository instrumentRepository;
    private final OrderRepository orderRepository;
    private final ApiService webservice;
    private final WorkManager workManager;

    @Inject
    public MainViewModel(TokenRepository tokenRepository, OwnedStockRepository ownedStockRepository,
                         InstrumentRepository instrumentRepository, OrderRepository orderRepository,
                         ApiService webservice, WorkManager workManager) {
        this.tokenRepository = tokenRepository;
        this.ownedStockRepository = ownedStockRepository;
        this.instrumentRepository = instrumentRepository;
        this.orderRepository = orderRepository;
        this.webservice = webservice;
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

    public LiveData<Order> placeOrder(String tokenString, OrderRequest orderRequest) {
        MutableLiveData<Order> orderMutableLiveData = new MutableLiveData<>();
        webservice.postOrder(tokenString, orderRequest).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Order order = response.body();
                orderRepository.saveOrder(order);
                orderMutableLiveData.setValue(order);
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
        return orderMutableLiveData;
    }

    public void startService() {

        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(GetQuoteWorker.class,
                15, TimeUnit.MINUTES)
                .addTag(Constants.TAG_GET_QUOTE)
                .setInputData(new Data.Builder().putString(Constants.KEY_SYMBOL, "GOOGL").build()).build();

        workManager.enqueue(workRequest);
    }

    public void stopService() {
        workManager.cancelAllWorkByTag(Constants.TAG_GET_QUOTE);
    }
}
