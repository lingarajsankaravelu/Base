package lingaraj.hourglass.in.base.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import lingaraj.hourglass.in.base.utils.Constants;

public class AppSharedPreference {

  private SharedPreferences mSharedPreference;
  public static class Keys {
    static final String KEY_TOKEN = "SERVER_TOKEN";
  }

  public AppSharedPreference(Context applicationContext){
  if (applicationContext instanceof Activity){
    throw new IllegalStateException("Need Application Context");
  }
  else {
    mSharedPreference = applicationContext.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
  }
  }

  public void setToken(String token){
    mSharedPreference.edit().putString(Keys.KEY_TOKEN,token).apply();
  }

  public String getToken(){
   return mSharedPreference.getString(Keys.KEY_TOKEN,null);
  }




}
