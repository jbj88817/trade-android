package us.bojie.tradebo.di.component;


import javax.inject.Singleton;

import dagger.Component;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.di.module.NetworkModule;

@Component(modules = NetworkModule.class)
@Singleton
public interface WorkerCommponent {
    ApiService getWebService();
}
