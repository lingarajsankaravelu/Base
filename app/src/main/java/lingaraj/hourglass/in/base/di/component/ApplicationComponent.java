package lingaraj.hourglass.in.base.di.component;

import dagger.Component;
import javax.inject.Singleton;
import lingaraj.hourglass.in.base.BaseApp;
import lingaraj.hourglass.in.base.api.di.APIModule;
import lingaraj.hourglass.in.base.di.modules.DatabaseModule;
import lingaraj.hourglass.in.base.di.modules.NetworkModule;
import lingaraj.hourglass.in.base.di.modules.ActivityBindingModule;
import lingaraj.hourglass.in.base.di.modules.ApplicationModule;
import lingaraj.hourglass.in.base.di.modules.ViewModelsModule;


/**
 * All singleton Modules goes here i.e application,ActivityBindingModule, database and network module
 */
@Singleton
@Component(modules = {
    ApplicationModule.class,
    ActivityBindingModule.class,
    NetworkModule.class,
    DatabaseModule.class,
    APIModule.class,
    ViewModelsModule.class
})
public interface ApplicationComponent {
 void inject(BaseApp application);
}

