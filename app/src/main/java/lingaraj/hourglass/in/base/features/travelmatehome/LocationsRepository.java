package lingaraj.hourglass.in.base.features.travelmatehome;

import android.os.Handler;
import androidx.annotation.NonNull;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import lingaraj.hourglass.in.base.api.APIRouter;
import lingaraj.hourglass.in.base.base.AppSharedPreference;
import lingaraj.hourglass.in.base.base.lifecycle.DisposableManager;
import lingaraj.hourglass.in.base.database.AppDatabase;
import lingaraj.hourglass.in.base.database.location.Location;
import lingaraj.hourglass.in.base.di.Names;
import lingaraj.hourglass.in.base.di.ScreenScope;
import timber.log.Timber;

public class LocationsRepository {

  private final AppDatabase database;
  private final APIRouter router;
  private CompositeDisposable manager = new CompositeDisposable();
  private final String errorMessageCommon;
  private final AppSharedPreference preference;


  @Inject
  LocationsRepository(AppDatabase database, APIRouter router,
     @Named(Names.NAMED_ERROR_COMMON) String errorMessageCommon, AppSharedPreference preference) {
    this.database = database;
    this.router = router;
    this.errorMessageCommon = errorMessageCommon;
    this.preference = preference;
  }

  public void getDataFromDB( LocationItemCallback callback){
    manager.add(this.database.locationDAO().getLocations()
        .flatMap(Flowable::fromIterable)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(callback::OnItemEmitted));
  }

  public void fetchFromNetwork(final ErrorCallback callback) {
    manager.add(router.getData()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(placesResponse ->{
          preference.setUserName(placesResponse.getCustomerName());
          if (placesResponse.getLocations()==null ||placesResponse.getLocations().size()==0 ){
            callback.onError(errorMessageCommon);
          }
          else {
            saveToDB(placesResponse.getLocations());
          }

        }, Timber::e));

  }


  private void saveToDB(@NonNull List<Location> locations) {
     manager.add(
         Completable.fromAction(new Action() {
        @Override
        public void run() throws Exception {
          database.locationDAO().insert(locations);
        }
      }).subscribe());
  }

  public void getRecordFromDB(long rowId,final LocationItemCallback locationItemCallback) {
     manager.add(Completable.fromAction(new Action() {
       @Override public void run() throws Exception {
          Location location =  database.locationDAO().getLocation(rowId);
          locationItemCallback.OnItemEmitted(location);
       }
     }).subscribe());
  }
}
