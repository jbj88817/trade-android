package us.bojie.tradebo.di.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import us.bojie.tradebo.di.key.ViewModelKey;
import us.bojie.tradebo.ui.viewmodels.FactoryViewModel;
import us.bojie.tradebo.ui.viewmodels.MainViewModel;


@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
