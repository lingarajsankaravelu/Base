package lingaraj.hourglass.in.base.di.modules;

import android.content.Context;
import android.content.res.Configuration;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import lingaraj.hourglass.in.base.BaseApp;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.base.AppSharedPreference;
import lingaraj.hourglass.in.base.di.Names;
import timber.log.Timber;

@Module
public class ApplicationModule {

    private final BaseApp application;
    private final String errorMessageCommon;
    private final String messageCommonLoading;
    private final AppSharedPreference appSharedPreference;
    private final Gson gson;
    private final boolean isTab;
    private final ThreadPoolExecutor executors;



    public ApplicationModule(BaseApp application) {
        this.application = application;
        this.appSharedPreference = new AppSharedPreference(application);
        this.errorMessageCommon = application.getString(R.string.error_message_common);
        this.messageCommonLoading = application.getString(R.string.message_common_loading);
        this.gson = new Gson();
        this.isTab = (application.getResources().getConfiguration().screenLayout
            & Configuration.SCREENLAYOUT_SIZE_MASK)
            >= Configuration.SCREENLAYOUT_SIZE_LARGE;
        int no_of_cores = Runtime.getRuntime().availableProcessors() * 2;
        this.executors = new ThreadPoolExecutor(no_of_cores, no_of_cores,60L, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
        Timber.d("NO. of cores used for Thread Pool *2:"+no_of_cores);

    }

    @Provides
    Gson providesGson(){
        return this.gson;
    }

    @Provides
    AppSharedPreference providesAppSharedPreference(){ return this.appSharedPreference; }

    @Provides
    Context provideApplicationContext() {
        return application;
    }

    @Provides @Named(Names.NAMED_ERROR_COMMON) String providesErrorMessageCommon(){
        return this.errorMessageCommon;
    }

    @Provides @Named(Names.NAMED_LOADING) String providesMessageLoading(){
        return this.messageCommonLoading;
    }

    @Provides
    ThreadPoolExecutor providesThreadPoolExecutor(){
        return this.executors;
    }

    @Provides
    @Named(Names.IS_TAB)
    Boolean providesIsTab(){
        return isTab;
    }


}
