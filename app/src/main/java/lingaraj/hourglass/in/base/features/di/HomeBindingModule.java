package lingaraj.hourglass.in.base.features.di;

import androidx.fragment.app.Fragment;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import lingaraj.hourglass.in.base.features.detaillocationinfo.LocationDetailComponent;
import lingaraj.hourglass.in.base.features.detaillocationinfo.LocationDetailFragment;
import lingaraj.hourglass.in.base.features.travelmatehome.di.LocationsComponent;
import lingaraj.hourglass.in.base.features.travelmatehome.LocationsFragment;

/**
 * Component of all the fragments component which is added to HomeActivity goes here
 */
@Module(subcomponents = { LocationsComponent.class
    ,LocationDetailComponent.class })
public abstract class HomeBindingModule {

  @Binds
  @IntoMap
  @FragmentKey(LocationsFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindsLocationsFragment(LocationsComponent.Builder builder);


  @Binds
  @IntoMap
  @FragmentKey(LocationDetailFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindsLocationDetailFragment(LocationDetailComponent.Builder builder);


}
