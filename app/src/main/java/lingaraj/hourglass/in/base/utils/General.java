package lingaraj.hourglass.in.base.utils;

import android.os.Bundle;
import java.util.UUID;
import timber.log.Timber;

public class General {

  public static Bundle generateBaseBundle(){
    Bundle bundle = new Bundle();
    String instance_id = UUID.randomUUID().toString();
    Timber.d("Bundle with Instance Id:"+instance_id);
    bundle.putString(Constants.INSTANCE_ID,instance_id);
    return bundle;
  }
}
