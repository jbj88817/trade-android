package us.bojie.tradebo.ui.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import us.bojie.tradebo.R;
import us.bojie.tradebo.database.entity.Instrument;
import us.bojie.tradebo.database.entity.OwnedStock;
import us.bojie.tradebo.database.entity.Token;
import us.bojie.tradebo.ui.viewmodels.MainViewModel;
import us.bojie.tradebo.utils.Constants;
import us.bojie.tradebo.utils.StringUtils;
import us.bojie.tradebo.utils.TokenUtils;

public class MainFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    TokenUtils tokenUtil;
    @Inject
    FirebaseFirestore db;
    @Inject
    SharedPreferences sharedPreferences;

    private MainViewModel mViewModel;

    // FOR DESIGN
    @BindView(R.id.tv_init)
    TextView initTextView;
    @BindView(R.id.btn_start)
    Button startButton;
    @BindView(R.id.btn_stop)
    Button stopButton;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onAttach(Context context) {
        this.configureDagger();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureViewModel();
        this.initComponents();
    }

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }


    private void configureViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        mViewModel.init();
        mViewModel.getToken().observe(this, this::updateTokenString);
    }

    private void updateTokenString(@Nullable Token token) {
        if (token != null) {
            tokenUtil.updateTokenString(token);
            mViewModel.getOwnedStocksList(tokenUtil.getTokenString(), true)
                    .observe(this, this::updateInstruments);
        }
    }

    private void updateInstruments(@Nullable List<OwnedStock> ownedStockList) {
        if (ownedStockList != null) {
            AtomicInteger count = new AtomicInteger(ownedStockList.size());
            Set<String> set = new ConcurrentSkipListSet<>();
            for (OwnedStock ownedStock : ownedStockList) {
                LiveData<Instrument> instrumentLiveData = mViewModel
                        .getInstrument(StringUtils.getInstrumentIdFromUrl(ownedStock.getInstrument()));
                sharedPreferences.edit().putString(ownedStock.getInstrument(), ownedStock.getAverageBuyPrice()).apply();
                instrumentLiveData.observe(this, instrument -> {
                    initTextView.setText(getString(R.string.initialized));
                    String symbol = instrument.getSymbol();
                    String url = instrument.getUrl();
                    String saved = sharedPreferences.getString(url, null);
                    if (saved != null) {
                        set.add(saved);
                    }
                    if (!set.contains(symbol)) {
                        set.add(symbol);
                        String avgPrice = sharedPreferences.getString(url, null);
                        saveInitInFirebase(url, symbol, avgPrice);
                        sharedPreferences.edit().putString(url, symbol).apply();
                        count.getAndDecrement();
                        if (count.get() == 0) {
                            instrumentLiveData.removeObservers(this);
                        }
                    }
                });
            }
        }
    }

    private void saveInitInFirebase(String url, String symbol, String avgPrice) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.KEY_URL, url);
        map.put(Constants.KEY_AVG_PRICE, avgPrice);
        db.collection(Constants.KEY_INSTRUMENTS).document(symbol).set(map);
    }

    private void initComponents() {
        startButton.setOnClickListener(v -> {
            mViewModel.startService();
            Toast.makeText(getContext(), "Started !!!", Toast.LENGTH_SHORT).show();
            initTextView.setText("Started !!!");
        });

        stopButton.setOnClickListener(v -> {
            mViewModel.stopService();
            Toast.makeText(getContext(), "Stopped !!!", Toast.LENGTH_SHORT).show();
            initTextView.setText("Stopped !!!");
        });
    }
}

//            String instrumentUrl = ownedStock.getInstrument();
//            String quantity = ownedStock.getQuantity();
//            String instrumentId = StringUtils.getInstrumentIdFromUrl(instrumentUrl);
//            mViewModel.getInstrument(instrumentId).observe(this, instrument -> {
//                final String symbol = instrument.getSymbol();
//                mViewModel.getQuote(symbol).observe(this, quote -> {
//                    OrderRequest request = new OrderRequest.Builder(instrumentUrl, symbol,
//                            "sell", quantity, "1000").build();
////                    mViewModel.placeOrder(tokenUtil.getTokenString(), request)
////                            .observe(this, order -> midTextView.setText(order.getCancel()));
//
//                });
//            });

