package lingaraj.hourglass.in.base.api;

import io.reactivex.Single;
import lingaraj.hourglass.in.base.api.response.PlacesResponse;
import lingaraj.hourglass.in.base.api.response.Status;
import retrofit2.http.GET;

public interface APIRouter {

  @GET("/v2/5c261ccb3000004f0067f6ec")
  Single<PlacesResponse> getData();
}
