package com.msk.observer.observers;

import com.msk.observer.subject.WeatherData;

public class ForecastDisplay implements Observer, DisplayElement {

    private final WeatherData weatherData;
    private String forecast;

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
        this.forecast = "No forecast available";
    }

    @Override
    public void display() {
        System.out.println("Forecast: " + forecast);
    }

    @Override
    public void update() {
        float temperature = weatherData.getTemperature();
        if (temperature < 70) {
            forecast = "Cold weather ahead";
        } else if (temperature >= 70 && temperature <= 90) {
            forecast = "Moderate weather expected";
        } else {
            forecast = "Hot weather ahead";
        }
        display();
    }
}
