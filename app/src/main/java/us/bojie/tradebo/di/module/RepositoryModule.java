package us.bojie.tradebo.di.module;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.database.dao.TokenDao;
import us.bojie.tradebo.repositories.TokenRepository;

@Module
public class RepositoryModule {

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    TokenRepository provideTokenRepository(ApiService webservice, TokenDao tokenDao, Executor executor) {
        return new TokenRepository(webservice, tokenDao, executor);
    }
}
