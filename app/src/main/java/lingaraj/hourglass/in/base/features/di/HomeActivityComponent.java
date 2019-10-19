package lingaraj.hourglass.in.base.features.di;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import lingaraj.hourglass.in.base.di.ActivityScope;
import lingaraj.hourglass.in.base.features.HomeActivity;
import lingaraj.hourglass.in.base.di.modules.ActivityViewInterceptorModule;
import lingaraj.hourglass.in.base.di.modules.NavigationModule;

@ActivityScope
@Subcomponent(modules = { ActivityViewInterceptorModule.class
    ,HomeBindingModule.class,
    NavigationModule.class })
public abstract interface HomeActivityComponent extends AndroidInjector<HomeActivity> {

  @Subcomponent.Builder abstract class Builder extends AndroidInjector.Builder<HomeActivity>{

    @Override public void seedInstance(HomeActivity instance) {

    }
  }

}
