package digital.neuron.weatherapi.network;

import digital.neuron.weatherapi.data.WeatherData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("weather")
    Call<WeatherData> getWeather(@Query("id") int cityId);

}
