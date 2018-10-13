package us.bojie.tradebo.di.component;

import android.app.Application;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import us.bojie.tradebo.App;
import us.bojie.tradebo.di.module.AppModule;


@Singleton
@Component(modules={AndroidSupportInjectionModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
