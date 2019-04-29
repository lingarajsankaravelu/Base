package lingaraj.hourglass.in.base.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import timber.log.Timber;

@Singleton
public class BaseViewModelFactory implements ViewModelProvider.Factory {

  private final Map<Class<? extends ViewModel>, Provider<ViewModel>> mProviderMap;

  @Inject
  public BaseViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
    mProviderMap = providerMap;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    Provider<? extends ViewModel> creator = mProviderMap.get(modelClass);
    if (creator == null) {
      Timber.d("No previous model found in map:"+modelClass.getSimpleName());
      for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : mProviderMap.entrySet()) {
        if (modelClass.isAssignableFrom(entry.getKey())) {
          creator = entry.getValue();
          Timber.d("Created key for model in map:"+modelClass.getSimpleName()+"->"+entry.getKey());
          break;
        }
      }
    }
    if (creator == null) {
      Timber.d("Unkown model class:"+modelClass.getSimpleName());
      throw new IllegalArgumentException("unknown model class " + modelClass);
    }
    try {
      return (T) creator.get();
    } catch (Exception e) {
      Timber.e(e);
      throw new RuntimeException(e);
    }
  }
}