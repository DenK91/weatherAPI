package digital.neuron.weatherapi.data;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                ", description='" + description + '\'' +
                '}';
    }
}
