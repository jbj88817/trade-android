package us.bojie.tradebo.di.component;

import javax.inject.Singleton;

import androidx.fragment.app.Fragment;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules={AndroidSupportInjectionModule.class})
public interface FragmentComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder fragment(Fragment fragment);
        FragmentComponent build();
    }

    void inject(Fragment fragment);
}
