package lingaraj.hourglass.in.base.home.fragment;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.View;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.base.BaseFragment;
import lingaraj.hourglass.in.base.databinding.FragmentMainBinding;
import lingaraj.hourglass.in.base.utils.General;

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

  private FragmentMainBinding binding;
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
    binding  = DataBindingUtil.bind(view);
    if (binding!=null){
      binding.swipeForRefresh.setOnRefreshListener(this);
    }
  }

  @Override
  public void onRefresh() {
    showLoader();
  }

  public void showLoader(){

  }
}
