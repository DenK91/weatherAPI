package digital.neuron.weatherapi.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApi {

    private static final String ENDPOINT = "http://api.openweathermap.org/data/2.5/";

    private Retrofit retrofit;
    private WeatherService weatherService;

    private static WeatherApi INSTANCE;

    private static WeatherApi getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WeatherApi();
        }
        return INSTANCE;
    }

    private WeatherApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new ApiInterceptor())
                        .addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static WeatherService getWeatherService() {
        if (getInstance().weatherService == null) {
            getInstance().weatherService = getInstance().retrofit.create(WeatherService.class);
        }
        return getInstance().weatherService;
    }

    private class ApiInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            return chain.proceed(
                    chain.request().newBuilder().url(chain.request().url().newBuilder()
                            .addQueryParameter("APPID", "c995cd60b654b0aebbd2659eaee1b118")
                            .build()).build());
        }
    }
}
