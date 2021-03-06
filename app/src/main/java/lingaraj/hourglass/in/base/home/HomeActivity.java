package lingaraj.hourglass.in.base.home;

import androidx.fragment.app.Fragment;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.base.BaseActivity;
import lingaraj.hourglass.in.base.home.fragment.HomeFragment;
import timber.log.Timber;

public class HomeActivity extends BaseActivity {

  @Override protected int layoutRes() {
    Timber.d("Layout Supplied");
    return R.layout.activity_main;
  }

  @Override public Fragment initialScreen() {
    return HomeFragment.newInstance();
  }
}
