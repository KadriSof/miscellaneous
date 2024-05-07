package com.msk.observer;

/*
Design Pattern: Observer
    The Observer Pattern defines a one-to-many dependency between
    objects so that when one object changes state, all of its dependents
    are notified and updated automatically
 */

import com.msk.observer.observers.CurrentConditionsDisplay;
import com.msk.observer.observers.DisplayElement;
import com.msk.observer.observers.ForecastDisplay;
import com.msk.observer.observers.StatisticsDisplay;
import com.msk.observer.subject.WeatherData;

public class WeatherStation {
    
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentConditionsDisplay =
                new CurrentConditionsDisplay(weatherData);

        ForecastDisplay forecastDisplay =
                new ForecastDisplay(weatherData);

        StatisticsDisplay statisticsDisplay =
                new StatisticsDisplay(weatherData);

        weatherData.registerObserver(currentConditionsDisplay);
        weatherData.registerObserver(forecastDisplay);
        weatherData.registerObserver(statisticsDisplay);

        // Simulate weather measurements changing
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);

        // Remove an observer (e.g., if display device is no longer needed)
        weatherData.removeObserver(currentConditionsDisplay);
    }
}
