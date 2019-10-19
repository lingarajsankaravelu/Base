package lingaraj.hourglass.in.base.features.travelmatehome;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.snackbar.Snackbar;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.base.AppSharedPreference;
import lingaraj.hourglass.in.base.base.BaseFragment;
import lingaraj.hourglass.in.base.base.BaseViewModelFactory;
import lingaraj.hourglass.in.base.database.location.Location;
import lingaraj.hourglass.in.base.databinding.FragmentLocationsBinding;
import lingaraj.hourglass.in.base.utils.General;
import timber.log.Timber;

public class LocationsFragment extends BaseFragment {

  private String TAG = "HOMEFRAG";
  private FragmentLocationsBinding binding;
  @Inject BaseViewModelFactory viewModelFactory;
  @Inject AppSharedPreference preference;
  private boolean loading = true;
  private LocationsViewModel view_model;
  private LocationsAdapter adapter;
  private Context mcontext;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    this.mcontext = context;
  }

  public static LocationsFragment newInstance(){
    LocationsFragment homeFragment = new LocationsFragment();
    Bundle bundle = General.generateBaseBundle();
    homeFragment.setArguments(bundle);
    return homeFragment;
  }

  @Override protected int layoutRes() {
   return R.layout.fragment_locations;

  }

  @Override protected void onViewBound(View view) {
     binding = DataBindingUtil.bind(view);
     view_model = viewModelFactory.create(LocationsViewModel.class);
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    init();
    setObservers();
    view_model.loadData();
  }

  private void init() {
    adapter = new LocationsAdapter(this.mcontext,new ItemClick());
    binding.dataGroup.setVisibility(View.GONE);
    binding.locations.setHasFixedSize(false);
    binding.locations.setLayoutManager(new LinearLayoutManager(this.mcontext,LinearLayoutManager.VERTICAL,false));
    binding.locations.setAdapter(adapter);

  }

  private void setObservers() {
    view_model.getLocationData().observe(this, new Observer<Location>() {
      @Override public void onChanged(Location location) {
        if (location!=null){
          addLocation(location);
        }

      }
    });
    view_model.getMessages().observe(this, new Observer<String>() {
      @Override public void onChanged(String message) {
        if (message!=null){
          showSnackbar(message);
        }
      }
    });

  }

  private void showSnackbar(String message) {
    Snackbar.make(binding.parent,message,Snackbar.LENGTH_SHORT).show();
    Timber.d("Message:"+message);
  }

  private void addLocation(Location location) {
    adapter.addItem(location);
    if (loading){
      loading = false;
      binding.userName.setText(this.mcontext.getString(R.string.user_name,preference.getUserName()));
      binding.dataGroup.setVisibility(View.VISIBLE);
    }


  }

  public class ItemClick implements View.OnClickListener {
    @Override
    public void onClick(View v) {
      int position = binding.locations.getChildAdapterPosition(v);
      Timber.d("Clicked:"+position);
      Location data = adapter.getItem(position);
      view_model.userSelectedLocation(data);
    }
  }
}
