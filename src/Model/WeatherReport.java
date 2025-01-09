/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import ConnexionHTTP.Callback;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author apeyt
 */
public class WeatherReport extends Observable implements Callback  {
    String main, description;
    double temp, temp_min, temp_max;
    double lon, lat;
    ImageIcon icon;

    public WeatherReport() {
      temp=0.0;
      temp_min=0.0;
      temp_max=0.0;
      lon=0.0;
      lat=0.0;
      main = new String();
      description = new String();
      icon = null;
    }

    @Override
    public String toString() {
        return "WeatherReport{" + "main=" + main + ", description=" + description + ", temp=" + temp + ", temp_min=" + temp_min + ", temp_max=" + temp_max + ", lon=" + lon + ", lat=" + lat + '}';
    }

    public String getMain() {
        return main;
    }
    
    public String getDescription() {
        return description;
    }

    public double getTemp() {
        return temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
    
    public ImageIcon getIcon() {
        return icon;
    }
    

    public void updateWeatherData(JSONObject jsonObj) throws JSONException {
        this.main = jsonObj.getJSONArray("weather").getJSONObject(0).getString("main");
        this.description = jsonObj.getJSONArray("weather").getJSONObject(0).getString("description");
        this.temp = jsonObj.getJSONObject("main").getDouble("temp");
        this.temp_min = jsonObj.getJSONObject("main").getDouble("temp_min");
        this.temp_max = jsonObj.getJSONObject("main").getDouble("temp_max");
        this.lon = jsonObj.getJSONObject("coord").getDouble("lon");
        this.lat = jsonObj.getJSONObject("coord").getDouble("lat");
        
        String iconCode = jsonObj.getJSONArray("weather").getJSONObject(0).getString("icon");
        this.icon = new ImageIcon("https://openweathermap.org/img/wn/" + iconCode + "@2x.png");
    }

    @Override
    public void onWorkDone(JSONObject jsonObj) throws JSONException{
        updateWeatherData(jsonObj);
        
        setChanged();
        notifyObservers();
    }
}
