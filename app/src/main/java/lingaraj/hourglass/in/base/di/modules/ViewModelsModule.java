package lingaraj.hourglass.in.base.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import java.util.Map;
import javax.inject.Provider;
import javax.inject.Singleton;
import lingaraj.hourglass.in.base.base.BaseViewModelFactory;
import lingaraj.hourglass.in.base.di.ViewModelKey;
import lingaraj.hourglass.in.base.home.fragment.di.HomeFragmentViewModel;

/**
 * All ViewModels binded here with dagger
 */

@Module
public abstract class ViewModelsModule {

  @Binds
  abstract ViewModelProvider.Factory bindsViewModelFactory(BaseViewModelFactory baseViewModelFactory);

  @Binds
  @IntoMap
  @ViewModelKey(HomeFragmentViewModel.class)
  abstract ViewModel bindsHomeFargmentViewModel(HomeFragmentViewModel homeFragmentViewModel);

}
