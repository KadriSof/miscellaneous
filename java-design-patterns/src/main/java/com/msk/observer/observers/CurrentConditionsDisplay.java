package com.msk.observer.observers;

import com.msk.observer.subject.Subject;
import com.msk.observer.subject.WeatherData;

public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private final WeatherData weatherData;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        float temperature = weatherData.getTemperature();
        float humidity = weatherData.getHumidity();
        float pressure = weatherData.getPressure();

        System.out.println("Current Conditions: " + temperature + "F degrees and "
                + humidity + "% humidity and " + pressure + "% pressure");
    }

    @Override
    public void update() {
        float temperature = weatherData.getTemperature();
        float humidity = weatherData.getHumidity();
        float pressure = weatherData.getPressure();

        display();
    }
}
