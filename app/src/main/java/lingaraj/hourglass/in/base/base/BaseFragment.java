package lingaraj.hourglass.in.base.base;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;
import java.util.Set;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.di.injectors.Injector;
import lingaraj.hourglass.in.base.base.lifecycle.ScreenLifecycleTask;


public abstract class BaseFragment extends Fragment {

    @Inject
    Set<ScreenLifecycleTask> screenLifecycleTasks;


    @Override
    public void onAttach(Context context) {
        Injector.inject(this);
        super.onAttach(context);
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutRes(), container, false);
        onViewBound(view);
        for (ScreenLifecycleTask task : screenLifecycleTasks) {
            task.onEnterScope(view.getRootView());
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        for (ScreenLifecycleTask task : screenLifecycleTasks) {
            task.onExitScope();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (ScreenLifecycleTask task : screenLifecycleTasks) {
            task.onDestroy();
        }
        if (!Objects.requireNonNull(getActivity()).isChangingConfigurations()) {
            Injector.clearComponent(this);
        }
    }

    protected abstract void onViewBound(View view);

    @LayoutRes
    protected abstract int layoutRes();
}
