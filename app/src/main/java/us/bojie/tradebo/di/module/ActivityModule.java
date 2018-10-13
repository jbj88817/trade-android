package us.bojie.tradebo.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import us.bojie.tradebo.ui.activities.MainActivity;


@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();
}
