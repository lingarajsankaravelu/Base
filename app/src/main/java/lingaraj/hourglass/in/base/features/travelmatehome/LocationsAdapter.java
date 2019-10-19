package lingaraj.hourglass.in.base.features.travelmatehome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.database.location.Location;
import lingaraj.hourglass.in.base.databinding.LayoutLocationBinding;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.ViewHolder> {

  private final Context mcontext;
  private final LocationsFragment.ItemClick itemClick;
  private final LocationsFragment.FavouriteClick favouriteClick;
  private List<Location> items = new ArrayList<>();
  private List<Integer> marked = new ArrayList<>();

  public LocationsAdapter(Context mcontext, LocationsFragment.ItemClick itemClick,
      LocationsFragment.FavouriteClick favouriteClick) {
    this.mcontext = mcontext;
    this.itemClick = itemClick;
    this.favouriteClick = favouriteClick;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(this.mcontext).inflate(
        R.layout.layout_location,parent,false);
    view.setOnClickListener(this.itemClick);
   ImageView image_view =  view.findViewById(R.id.favourite);
   image_view.setOnClickListener(this.favouriteClick);
   return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    if (this.marked.contains(position)){
      holder.binding.favourite.setImageResource(R.drawable.ic_like);
    }
    else {
      holder.binding.favourite.setImageResource(R.drawable.ic_un_like);

    }
    Location location = this.items.get(position);
    holder.binding.date.setText(location.getDate());
    holder.binding.place.setText(location.getPlace());
    Picasso.with(mcontext).load(location.getUrl()).resize(400,200).into(holder.binding.imageView);
  }

  public Location getItem(int position) {
    return items.get(position);
  }

  public void marked(int position) {
    if (this.marked.contains(position)){
      marked.remove(position);
    }
    else {
      this.marked.add(position);
    }
    notifyDataSetChanged();

  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    public LayoutLocationBinding binding;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      binding = DataBindingUtil.bind(itemView);
      binding.imageView.setClipToOutline(true);
      binding.parent.setClipToOutline(true);
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
