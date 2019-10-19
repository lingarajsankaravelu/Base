package lingaraj.hourglass.in.base.features.travelmatehome;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.jakewharton.rxrelay2.BehaviorRelay;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.database.location.Location;
import org.jetbrains.annotations.NotNull;

public class LocationsViewModel extends ViewModel {

  private final LocationsRepository repository;
  private final MutableLiveData<Location> locationData = new MutableLiveData<Location>();
  private final MutableLiveData<String> messages = new MutableLiveData<>();
  private final BehaviorRelay<Location> userSelectedLocation = BehaviorRelay.create();

  public MutableLiveData<Location> getLocationData() {
    return locationData;
  }

  public MutableLiveData<String> getMessages() {
    return messages;
  }

  public BehaviorRelay<Location> getUserSelectedLocation() {
    return userSelectedLocation;
  }

  @Inject
  public LocationsViewModel(LocationsRepository repository) {
    this.repository = repository;
  }

  public void userSelectedLocation(Location location){
    repository.setCachedLocationSelection(location);
  }

  public void requestUserSelectedLocation(){
    userSelectedLocation.accept(repository.getCachedLocationSelection());
  }

  public void loadData(){
    repository.getDataFromDB(new LocationItemCallback() {
      @Override public
      void OnItemEmitted(@NotNull Location location) {
        locationData.postValue(location);

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
