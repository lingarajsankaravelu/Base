package lingaraj.hourglass.in.base.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
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
