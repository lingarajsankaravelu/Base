package lingaraj.hourglass.in.base.features.travelmatehome;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.gson.Gson;
import com.jakewharton.rxrelay2.BehaviorRelay;
import io.reactivex.Observable;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.database.location.Location;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public class LocationsViewModel extends ViewModel {

  private final LocationsRepository repository;
  private final BehaviorRelay<Location> locationData = BehaviorRelay.create();
  private final MutableLiveData<String> messages = new MutableLiveData<>();
  private final BehaviorRelay<Location> userSelectedLocation = BehaviorRelay.create();

  public Observable<Location> getLocationData() {
    return locationData;
  }

  public MutableLiveData<String> getMessages() {
    return messages;
  }

  public Observable<Location> getUserSelectedLocation() {
     return userSelectedLocation;
  }

  public void requestUserSelectedLocation(long rowId){
    repository.getRecordFromDB(rowId,new LocationItemCallback() {
      @Override public void OnItemEmitted(@NotNull Location location) {
           userSelectedLocation.accept(location);
      }
    });

  }
  @Inject
  LocationsViewModel(LocationsRepository repository) {
    this.repository = repository;
  }


  public void loadData(){
    repository.getDataFromDB(new LocationItemCallback() {
      @Override public
      void OnItemEmitted(@NotNull Location location) {
        Timber.d("Item Recieved:"+new Gson().toJson(location));
        locationData.accept(location);

      }
    });

    repository.fetchFromNetwork(new ErrorCallback() {
      @Override
      public void onError(@NotNull String message) {
         messages.postValue(null);
         messages.postValue(message);
      }
    });
  }


}
