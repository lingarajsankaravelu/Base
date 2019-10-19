package lingaraj.hourglass.in.base.features.travelmatehome;

import androidx.annotation.NonNull;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Named;
import lingaraj.hourglass.in.base.api.APIRouter;
import lingaraj.hourglass.in.base.base.AppSharedPreference;
import lingaraj.hourglass.in.base.base.lifecycle.DisposableManager;
import lingaraj.hourglass.in.base.database.AppDatabase;
import lingaraj.hourglass.in.base.database.location.Location;
import lingaraj.hourglass.in.base.di.Names;
import timber.log.Timber;

public class LocationsRepository {

  private final AppDatabase database;
  private final APIRouter router;
  private final DisposableManager manager;
  private final String errorMessageCommon;
  private final AppSharedPreference preference;
  private Location cachedLocationSelection;

  public Location getCachedLocationSelection() {
    return cachedLocationSelection;
  }

  public void setCachedLocationSelection(Location cachedLocationSelection) {
    this.cachedLocationSelection = cachedLocationSelection;
  }

  public LocationsRepository(AppDatabase database, APIRouter router, DisposableManager manager,
     @Named(Names.NAMED_ERROR_COMMON) String errorMessageCommon, AppSharedPreference preference) {
    this.database = database;
    this.router = router;
    this.manager = manager;
    this.errorMessageCommon = errorMessageCommon;
    this.preference = preference;
  }

  public void getDataFromDB( LocationItemCallback callback){
    manager.add(this.database.locationDAO().getLocations()
        .flatMap(Flowable::fromIterable)
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(callback::OnItemEmitted));
  }

  public void fetchFromNetwork(final ErrorCallback callback) {
    manager.add(router.getData()
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
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
}
