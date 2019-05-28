package lingaraj.hourglass.in.base.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.di.ActivityScope;
import lingaraj.hourglass.in.base.base.lifecycle.ActivityLifecycleTask;
import timber.log.Timber;

@ActivityScope
public class DefaultScreenNavigator extends ActivityLifecycleTask implements ScreenNavigator {

    private FragmentManager fragmentManager;
    private AppCompatActivity activity;

    @Inject
    DefaultScreenNavigator() {

    }

    @Override
    public void onCreate(AppCompatActivity activity) {
        this.activity = activity;
        init(activity.getSupportFragmentManager(), ((ScreenProvider) activity).initialScreen());
    }

    private void init(FragmentManager fragmentManager, Fragment rootScreen) {
        this.fragmentManager = fragmentManager;
        if (fragmentManager.getFragments().size() == 0) {
            fragmentManager.beginTransaction()
                    .replace(R.id.screen_container, rootScreen)
                    .commit();
        }
    }

    @Override
    public boolean pop() {
        return fragmentManager != null && fragmentManager.popBackStackImmediate();
    }


    @Override
    public void onBackPressed() {
        if (fragmentManager != null) {
            pop();
        }
    }


    @Override
    public void finish() {
        activity.finish();
    }


    @Override
    public void onDestroy(AppCompatActivity activity) {
        fragmentManager = null;
    }




     @Override
     public void showFragment(Fragment fragmentToBeLoaded, String tag){
         if (fragmentManager!=null){
             fragmentManager.beginTransaction()
                 .add(R.id.screen_container,fragmentToBeLoaded,tag)
                 .addToBackStack(tag).commit();
             Timber.d("Loading Fragment with backstack set"+tag);
         }
     }

     @Override
     public void showFragment(Fragment fragmentToBeLaoded){
         if (fragmentManager!=null){
             fragmentManager.beginTransaction().add(R.id.screen_container,fragmentToBeLaoded).commit();
             Timber.d("Loading fragment without backstack:"+fragmentToBeLaoded.getClass().getSimpleName());
         }
     }

    @Override
    public void goHome() {

    }




}
