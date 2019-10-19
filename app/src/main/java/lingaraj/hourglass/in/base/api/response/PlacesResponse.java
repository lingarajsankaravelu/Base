package lingaraj.hourglass.in.base.api.response;

import java.io.Serializable;
import java.util.List;
import lingaraj.hourglass.in.base.database.location.Location;

public class PlacesResponse implements Serializable {
  private String customerName;
  private List<Location> locations;

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public List<Location> getLocations() {
    return locations;
  }

  public void setLocations(List<Location> locations) {
    this.locations = locations;
  }
}
