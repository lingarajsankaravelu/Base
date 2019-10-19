package lingaraj.hourglass.in.base.features.travelmatehome;


import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import lingaraj.hourglass.in.base.database.location.Location;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.ViewHolder> {

  private final Context mcontext;
  private final LocationsFragment.ItemClick itemClick;
  private List<Location> items = new ArrayList<>();

  public LocationsAdapter(Context mcontext, LocationsFragment.ItemClick itemClick) {
    this.mcontext = mcontext;
    this.itemClick = itemClick;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    return null;
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
  }

  public Location getItem(int position) {
    return items.get(position);
  }

  public class ViewHolder extends RecyclerView.ViewHolder{

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }

  @Override public int getItemCount() {
     return items.size();
  }

  public void addItem(Location location){
    items.add(location);
    notifyDataSetChanged();
  }
}
