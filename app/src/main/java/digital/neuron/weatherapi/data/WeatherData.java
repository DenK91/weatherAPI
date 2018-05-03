package digital.neuron.weatherapi.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherData {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("base")
    private String base;

    @SerializedName("weather")
    private List<Weather> weather;

    @SerializedName("main")
    private Main main;

    @SerializedName("sys")
    private Sys sys;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", base='" + base + '\'' +
                ", weather=" + weather +
                ", main=" + main +
                ", sys=" + sys +
                '}';
    }
}
