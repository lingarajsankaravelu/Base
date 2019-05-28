package lingaraj.hourglass.in.base.home.fragment.di;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.base.lifecycle.ScreenLifecycleTask;
import lingaraj.hourglass.in.base.base.ScreenNavigator;
import timber.log.Timber;

public class HomeFragmentUIManager extends ScreenLifecycleTask {

  private ScreenNavigator navigator;


  @Inject
  HomeFragmentUIManager(ScreenNavigator screenNavigator){
    navigator = screenNavigator;
  }

  @Override public void onEnterScope(View view) {
    super.onEnterScope(view);
  }

  @Override public void onExitScope() {
    super.onExitScope();
  }

  @Override public void setToolbarTitles(@NonNull View view, @NonNull String title, @Nullable String subTitle) {
    super.setToolbarTitles(view, title, subTitle);
    Toolbar toolbar = (Toolbar) view;
    toolbar.setTitle(title);
    if (subTitle!=null){
      toolbar.setSubtitle(subTitle);
    }
    toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_white_24px);
    toolbar.setNavigationOnClickListener(v -> navigator.onBackPressed());
    Timber.d("Toolbar set");

  }

}
