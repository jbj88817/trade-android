package us.bojie.tradebo.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import us.bojie.tradebo.ui.fragments.MainFragment;


@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragmentFragment();
}
