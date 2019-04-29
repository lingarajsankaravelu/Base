package lingaraj.hourglass.in.base.di.modules;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import lingaraj.hourglass.in.base.base.lifecycle.ActivityLifecycleTask;
import lingaraj.hourglass.in.base.base.DefaultScreenNavigator;
import lingaraj.hourglass.in.base.base.ScreenNavigator;

/**
 * Striclty to be used with Activity Component Level.
 */

@Module
public abstract class NavigationModule {

    @Binds
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);

    @Binds
    @IntoSet
    abstract ActivityLifecycleTask bindScreenNavigatorTask(DefaultScreenNavigator screenNavigator);
}
