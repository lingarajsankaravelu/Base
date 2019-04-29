package lingaraj.hourglass.in.base.home.fragment;

import android.os.Bundle;
import android.view.View;
import java.util.UUID;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.base.BaseFragment;
import lingaraj.hourglass.in.base.utils.Constants;
import lingaraj.hourglass.in.base.utils.General;

public class HomeFragment extends BaseFragment {

  public static HomeFragment newInstance(){
    HomeFragment homeFragment = new HomeFragment();
    Bundle bundle = General.generateBaseBundle();
    homeFragment.setArguments(bundle);
    return homeFragment;
  }

  @Override protected int layoutRes() {
    return R.layout.fragment_main;
  }

  @Override protected void onViewBound(View view) {
    super.onViewBound(view);
  }
}
