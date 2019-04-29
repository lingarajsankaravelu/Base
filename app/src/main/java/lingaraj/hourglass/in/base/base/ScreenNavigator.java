package lingaraj.hourglass.in.base.base;

import android.support.v4.app.Fragment;

import java.util.List;

public interface ScreenNavigator {

    boolean pop();

    void onBackPressed();

    void finish();

    /**
     * @param fragmentToBeShown - the  fragment to be shown on screen
     * @param fragmentTag - A unique string name to represent the current fragment in Backstack
     */
    void showFragment(Fragment fragmentToBeShown, String fragmentTag);

    /**
     *
     * @param fragmentToBeLaoded - The fragment to be show on the screen.
     */
    void showFragment(Fragment fragmentToBeLaoded);

    void goHome();


}
