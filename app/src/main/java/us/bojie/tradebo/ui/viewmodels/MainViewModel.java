package us.bojie.tradebo.ui.viewmodels;


import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import us.bojie.tradebo.database.entity.Token;
import us.bojie.tradebo.repositories.TokenRepository;

public class MainViewModel extends ViewModel {
    private LiveData<Token> token;
    private TokenRepository mTokenRepository;

    @Inject
    public MainViewModel(TokenRepository tokenRepository) {
        mTokenRepository = tokenRepository;
    }

    public void init() {
        if (this.token != null) {
            return;
        }
        token = mTokenRepository.getToken();
    }

    public LiveData<Token> getToken() {
        return this.token;
    }
}
