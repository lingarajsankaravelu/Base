package lingaraj.hourglass.in.base.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import lingaraj.hourglass.in.base.utils.Constants;

public class AppSharedPreference {

  private SharedPreferences mSharedPreference;


  public static class Keys {
    static final String KEY_USER = "USER_NAME";
  }

  public AppSharedPreference(Context applicationContext){
  if (applicationContext instanceof Activity){
    throw new IllegalStateException("Need Application Context");
  }
  else {
    mSharedPreference = applicationContext.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
  }
  }

  public void setUserName(String token){
    mSharedPreference.edit().putString(Keys.KEY_USER,token).apply();
  }

  public String getUserName(){
   return mSharedPreference.getString(Keys.KEY_USER,null);
  }




}
