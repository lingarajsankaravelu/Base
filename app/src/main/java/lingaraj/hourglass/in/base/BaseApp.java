package lingaraj.hourglass.in.base;

import android.app.Application;
import com.jakewharton.threetenabp.AndroidThreeTen;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.di.component.ApplicationComponent;
import lingaraj.hourglass.in.base.di.component.DaggerApplicationComponent;
import lingaraj.hourglass.in.base.di.injectors.ActivityInjector;
import lingaraj.hourglass.in.base.di.modules.ApplicationModule;
import timber.log.Timber;

public class BaseApp  extends Application {
  private ApplicationComponent component;
  @Inject ActivityInjector activityInjector;

  @Override public void onCreate() {
    super.onCreate();
    AndroidThreeTen.init(this);
    setUpTimber();
    component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    component.inject(this);
  }

  private void setUpTimber() {
    Timber.plant(BuildConfig.DEBUG?new Timber.DebugTree():new TimberReleaseTree());

  }

  public ActivityInjector getActivityInjector() {
    return activityInjector;
  }


}
