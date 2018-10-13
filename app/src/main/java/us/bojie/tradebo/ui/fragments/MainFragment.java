package us.bojie.tradebo.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import us.bojie.tradebo.database.entity.Token;
import us.bojie.tradebo.ui.viewmodels.MainViewModel;

public class MainFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MainViewModel mViewModel;

    // FOR DESIGN
    @BindView(R.id.message)
    TextView midTextView;

    public static MainFragment newInstance() {
        return new MainFragment();
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
        this.configureDagger();
        this.configureViewModel();
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }


    private void configureViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.init();
        mViewModel.getToken().observe(this, this::updateUI);
    }

    // -----------------
    // UPDATE UI
    // -----------------

    private void updateUI(@Nullable Token token) {
        if (token != null) {
            midTextView.setText(token.getAccessToken());
        }
    }
}
