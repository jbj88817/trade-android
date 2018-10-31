package us.bojie.tradebo.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import androidx.work.WorkManager;
import dagger.Module;
import dagger.Provides;
import us.bojie.tradebo.utils.Constants;
import us.bojie.tradebo.utils.TokenUtils;

@Module(includes = {ViewModelModule.class, NetworkModule.class, RepositoryModule.class,
        DatabaseModule.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application app) {
        return app;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return application.getSharedPreferences(Constants.NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    TokenUtils providesTokenUtil(SharedPreferences preferences) {
        return new TokenUtils(preferences);
    }

    @Provides
    @Singleton
    WorkManager providesWorkManager() {
        return WorkManager.getInstance();
    }
}
