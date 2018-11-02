package us.bojie.tradebo.di.module;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.database.dao.InstrumentDao;
import us.bojie.tradebo.database.dao.OrderDao;
import us.bojie.tradebo.database.dao.OwnedStockDao;
import us.bojie.tradebo.database.dao.TokenDao;
import us.bojie.tradebo.repositories.InstrumentRepository;
import us.bojie.tradebo.repositories.OrderRepository;
import us.bojie.tradebo.repositories.OwnedStockRepository;
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

    @Provides
    @Singleton
    OwnedStockRepository provideOwnedStockRepository(ApiService webservice, OwnedStockDao ownedStockDao,
                                                     Executor executor, TokenRepository repository) {
        return new OwnedStockRepository(webservice, ownedStockDao, executor, repository);
    }

    @Provides
    @Singleton
    InstrumentRepository provideInstrumentRepository(ApiService webservice, InstrumentDao instrumentDao, Executor executor) {
        return new InstrumentRepository(webservice, instrumentDao, executor);
    }

    @Provides
    @Singleton
    OrderRepository provideOrderRepository(ApiService webservice, OrderDao orderDao, Executor executor) {
        return new OrderRepository(webservice, orderDao, executor);
    }
}
