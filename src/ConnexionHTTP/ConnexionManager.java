/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnexionHTTP;

import java.net.URI;

/**
 *
 * @author apeyt
 */
public class ConnexionManager {
    
    public static final String URL_Weather = "https://api.openweathermap.org/data/2.5/weather?q=lyon,fr&units=metric&lang=fr&appid=fa0a9c5fc803e4f2ae4d172009180c66";
    public static final String URL_Pollution = "https://api.openweathermap.org/data/2.5/air_pollution/forecast?lat=46.0398&lon=5.4133&units=metric&lang=fr&appid=fa0a9c5fc803e4f2ae4d172009180c66";
    private static ConnexionManager manager = null;
    private final Callback callWeather;
    private final Callback callPollution;
        
    private ConnexionManager(Callback callWeather,Callback callPollution){
        this.callWeather = callWeather;
        this.callPollution = callPollution;
    }
        
    public static ConnexionManager getConnexionManager(Callback callW, Callback callP){  // Point d'entrée du singleton : une seule instance possible
        if (manager==null){
            manager = new ConnexionManager(callW,callP); // Appel du contructeur privé 
        }
        return manager;
    }
    
    public void loadWeather() {
        ConnexionThread connexionThread = new ConnexionThread(callWeather, URL_Weather);
        connexionThread.start(); 
    }
    
    public void loadPollution(){
        ConnexionThread connexionThread = new ConnexionThread(callPollution, URL_Pollution);
        connexionThread.start(); 
    }
    
}
