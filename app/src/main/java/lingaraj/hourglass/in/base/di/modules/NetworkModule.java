package lingaraj.hourglass.in.base.di.modules;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import lingaraj.hourglass.in.base.BuildConfig;
import lingaraj.hourglass.in.base.base.AppSharedPreference;
import lingaraj.hourglass.in.base.utils.Constants;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {



  @Provides
  @Singleton
  static OkHttpClient provideHttpClient(HttpLoggingInterceptor logging,
      AppSharedPreference sharedPreference) {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    httpClient.addInterceptor(logging);
    if(sharedPreference.getToken()!=null && !sharedPreference.getToken().isEmpty()) {
      httpClient.addNetworkInterceptor(chain -> {
        Request original = chain.request();
        Request.Builder request_builder = original.newBuilder().addHeader("Authorization", "Bearer " + sharedPreference.getToken());
        Request request = request_builder.build();
        return chain.proceed(request);
      });
    }
    httpClient.readTimeout(6, TimeUnit.MILLISECONDS);
    httpClient.connectTimeout(6, TimeUnit.MILLISECONDS);

    return httpClient.build();
  }

  @Provides
  @Singleton
  static HttpLoggingInterceptor providesLoggingInterceptor(){
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    if (BuildConfig.DEBUG) {
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    }
    else {
      logging.setLevel(HttpLoggingInterceptor.Level.NONE);
    }
    return logging;


  }


  @Provides
  @Singleton
  static Retrofit providesRetrofit(OkHttpClient okHttpClient){
    return new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build();

  }



}
