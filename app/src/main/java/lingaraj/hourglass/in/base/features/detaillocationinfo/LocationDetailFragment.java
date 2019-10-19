package lingaraj.hourglass.in.base.features.detaillocationinfo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import lingaraj.hourglass.in.base.R;
import lingaraj.hourglass.in.base.base.BaseFragment;
import lingaraj.hourglass.in.base.base.BaseViewModelFactory;
import lingaraj.hourglass.in.base.database.location.Location;
import lingaraj.hourglass.in.base.databinding.FragmentLocationDetailsBinding;
import lingaraj.hourglass.in.base.features.travelmatehome.LocationsViewModel;
import lingaraj.hourglass.in.base.utils.General;

public class LocationDetailFragment extends BaseFragment implements View.OnClickListener {

  private FragmentLocationDetailsBinding binding;

  public static LocationDetailFragment newInstance() {
     LocationDetailFragment fragment = new LocationDetailFragment();
    Bundle args = General.generateBaseBundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Inject BaseViewModelFactory viewModelFactory;
  private LocationsViewModel viewModel;
  private CompositeDisposable disposable;
  private Context mcontext;
  @Override public void onAttach(Context context) {
    super.onAttach(context);
    this.mcontext = context;

  }

  @Override protected void onViewBound(View view) {
    binding = DataBindingUtil.bind(view);
    viewModel = viewModelFactory.create(LocationsViewModel.class);

  }

  @Override protected int layoutRes() {
    return R.layout.fragment_location_details;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    binding.bookNow.setOnClickListener(this);
    setObservers();

  }

  private void setObservers() {
    disposable.add(viewModel.getUserSelectedLocation().observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(location -> {
            if (location!=null){
              setReceivedDetails(location);
            }
        }));
  }

  private void setReceivedDetails(Location location) {
    Picasso.with(this.mcontext).load(location.getUrl()).into(binding.imageView);
    binding.date.setText(location.getDate());
    binding.place.setText(location.getPlace());
    binding.detailedText.setText(location.getDescription());
    binding.price.setText(this.mcontext.getString(R.string.price_per_person,String.valueOf(location.getRate())));


  }

  @Override public void onClick(View v) {
    if (v.getId()==R.id.book_now){
      Snackbar.make(binding.parent,"Book Now clicked ",Snackbar.LENGTH_SHORT).show();
    }

  }
}
