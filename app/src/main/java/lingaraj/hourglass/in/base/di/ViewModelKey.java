package lingaraj.hourglass.in.base.di;

import android.arch.lifecycle.ViewModel;
import dagger.MapKey;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {
  Class<? extends ViewModel> value();
}
