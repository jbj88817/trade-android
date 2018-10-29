package us.bojie.tradebo.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import us.bojie.tradebo.R;
import us.bojie.tradebo.database.entity.OwnedStock;
import us.bojie.tradebo.database.entity.Token;
import us.bojie.tradebo.ui.viewmodels.MainViewModel;
import us.bojie.tradebo.utils.StringUtils;
import us.bojie.tradebo.utils.TokenUtils;

public class MainFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    TokenUtils tokenUtil;

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
            for (OwnedStock ownedStock : ownedStockList) {
                mViewModel.getInstrument(StringUtils.getInstrumentIdFromUrl(ownedStock.getInstrument()))
                        .observe(this, instrument ->
                                initTextView.setText(getString(R.string.initialized)));
            }
        }
    }

    private void initComponents() {
        startButton.setOnClickListener(v -> mViewModel.startService());

        stopButton.setOnClickListener(v -> mViewModel.stopService());
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

