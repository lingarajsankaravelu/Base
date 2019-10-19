package lingaraj.hourglass.in.base.features.detaillocationinfo;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import lingaraj.hourglass.in.base.di.ScreenComponent;
import lingaraj.hourglass.in.base.di.ScreenScope;
import lingaraj.hourglass.in.base.di.modules.ScreenModule;
import lingaraj.hourglass.in.base.features.travelmatehome.LocationsFragment;

@ScreenScope
@Subcomponent(modules = { ScreenModule.class})
public interface LocationDetailComponent extends ScreenComponent<LocationDetailFragment> {

  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<LocationDetailFragment>{

  }

}

