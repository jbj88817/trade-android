package us.bojie.tradebo.di.component;


import javax.inject.Singleton;

import dagger.Component;
import us.bojie.tradebo.background.workers.RealWorker;
import us.bojie.tradebo.di.module.AppModule;

@Component(modules = {AppModule.class})
@Singleton
public interface WorkerComponent {
//    ApiService getWebService();
    void inject(RealWorker injector);
}
