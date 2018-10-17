package us.bojie.tradebo.di.module;

import dagger.Module;

@Module(includes = {ViewModelModule.class, NetworkModule.class, RepositoryModule.class,
        DatabaseModule.class})
public class AppModule {
}
