package lingaraj.hourglass.in.base.di.modules;

import dagger.Module;
import dagger.Provides;
import lingaraj.hourglass.in.base.base.ActivityViewInterceptor;

/**
 * Strictly Used only  in Activity Component level.
 */
@Module
public abstract class ActivityViewInterceptorModule {

    @Provides
    static ActivityViewInterceptor provideActivityViewInterceptor() {
        return ActivityViewInterceptor.DEFAULT;
    }
}
