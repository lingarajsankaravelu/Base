package lingaraj.hourglass.in.base.base.lifecycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import lingaraj.hourglass.in.base.di.ActivityScope;
import timber.log.Timber;

public abstract class ScreenLifecycleTask {

    /**
     * Callback received when a Screen becomes the visible screen.
     */
    public void onEnterScope(View view) {

    }

    /**
     * Callback received when a Screen is either popped of moved to the back stack.
     */
    public void onExitScope() {

    }

    /**
     * Callback received when a Screen is destroyed and will not be coming back (except as a new instance). This should
     * be used to clear any {@link ActivityScope} connections (Disposables, etc).
     */
    public void onDestroy() {

    }

    /**
     *
     * @param view  - view of the toolbar
     * @param title - title for the toolbar
     * @param subTitle - subtitle for the toolbar
     */
    public void setToolbarTitles(@NonNull View view, @NonNull String title,@Nullable String subTitle){
        if (!(view instanceof Toolbar)){
            Timber.d("Expected toolbar view, recieved something eles");
            throw new IllegalArgumentException("View of Toolbar expected");
        }

    }
}
