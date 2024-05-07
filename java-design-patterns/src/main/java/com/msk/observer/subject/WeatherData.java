package com.msk.observer.subject;

import com.msk.observer.observers.Observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject{

    private float temperature;
    private float humidity;
    private float pressure;
    private final List<Observer> observers;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }

    // We notify the Observers we get updated measurements from the Weather Station.
    public void measurementsChanged() {
        notifyObservers();
    }

    // We set the measurements and notify the Observers when we get new readings.
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
