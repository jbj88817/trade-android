package us.bojie.tradebo.di.module;

import android.app.Application;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import us.bojie.tradebo.database.MyDatabase;
import us.bojie.tradebo.database.dao.InstrumentDao;
import us.bojie.tradebo.database.dao.OrderDao;
import us.bojie.tradebo.database.dao.OwnedStockDao;
import us.bojie.tradebo.database.dao.TokenDao;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    MyDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                MyDatabase.class, "TradeBo.db")
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    TokenDao provideTokenDao(MyDatabase database) { return database.tokenDao(); }

    @Provides
    @Singleton
    OwnedStockDao provideOwnedStockDao(MyDatabase database) { return database.ownedStockDao(); }

    @Provides
    @Singleton
    InstrumentDao provideInstrumentDao(MyDatabase database) { return database.instrumentDao(); }

    @Provides
    @Singleton
    OrderDao provideOrderDao(MyDatabase database) { return database.orderDao(); }
}
