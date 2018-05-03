package digital.neuron.weatherapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import digital.neuron.weatherapi.data.WeatherData;
import digital.neuron.weatherapi.network.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());

    private TextView name;
    private TextView temp;
    private TextView sky;
    private TextView pressure;
    private TextView humidity;
    private TextView sunrise;
    private TextView sunset;
    private TextView fail;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        temp = findViewById(R.id.temp);
        sky = findViewById(R.id.sky);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);
        sunrise = findViewById(R.id.sunrise);
        sunset = findViewById(R.id.sunset);
        fail = findViewById(R.id.fail);
        progress = findViewById(R.id.progress);

        load();
    }

    private void load() {
        progress.setVisibility(View.VISIBLE);
        fail.setVisibility(View.GONE);

        WeatherApi.getWeatherService().getWeather(520555).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                progress.setVisibility(View.GONE);
                setWeather(response.body());
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                setWeather(null);
            }
        });
    }

    private void setWeather(WeatherData data) {
        progress.setVisibility(View.GONE);
        if (data != null) {
            name.setText(data.getName());
            temp.setText(String.valueOf((int)(data.getMain().getTemp() -273.15)) + " C");
            if (data.getWeather() != null && !data.getWeather().isEmpty()) {
                sky.setText(data.getWeather().get(0).getDescription());
            } else {
                sky.setText("Неизвестно");
            }
            pressure.setText(String.valueOf((int)(data.getMain().getPressure() * 0.750062)) + " mm");
            humidity.setText(String.valueOf((int)data.getMain().getHumidity()) + "%");
            sunrise.setText(String.valueOf(timeFormatter.format(data.getSys().getSunrise() * 1000)));
            sunset.setText(String.valueOf(timeFormatter.format(data.getSys().getSunset() * 1000)));
        } else {
            fail.setVisibility(View.VISIBLE);
            fail.setText("Ошибка при загрузке");
        }
    }
}
