package us.bojie.tradebo.di.module;

import android.app.Application;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import us.bojie.tradebo.database.MyDatabase;
import us.bojie.tradebo.database.dao.TokenDao;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    MyDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                MyDatabase.class, "MyDatabase.db")
                .build();
    }

    @Provides
    @Singleton
    TokenDao provideUserDao(MyDatabase database) { return database.tokenDao(); }
}
