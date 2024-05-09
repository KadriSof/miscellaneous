package com.msk.observer.observers;

import com.msk.observer.subject.WeatherData;

public class StatisticsDisplay implements Observer, DisplayElement {

    private final WeatherData weatherData;
    private float minTemperature;
    private float maxTemperature;
    private float avgTemperature;
    private int numMeasurements;

    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
        this.minTemperature = Float.MAX_VALUE;
        this.maxTemperature = Float.MIN_VALUE;
        this.avgTemperature = 0;
        this.numMeasurements = 0;
    }

    @Override
    public void display() {
        System.out.println("Statistics:");
        System.out.println("  - Minimum Temperature: " + minTemperature);
        System.out.println("  - Maximum Temperature: " + maxTemperature);
        System.out.println("  - Average Temperature: " + avgTemperature);
        System.out.println("  - Number of Measurements: " + numMeasurements);
    }

    @Override
    public void update() {
        float temperature = weatherData.getTemperature();

        if (temperature < minTemperature) {
            minTemperature = temperature;
        }
        if (temperature > maxTemperature) {
            maxTemperature = temperature;
        }

        avgTemperature = ((avgTemperature * numMeasurements) + temperature) / (numMeasurements + 1);
        numMeasurements++;

        display();
    }
}
