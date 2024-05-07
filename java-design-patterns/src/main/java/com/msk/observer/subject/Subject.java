package com.msk.observer.subject;

import com.msk.observer.observers.Observer;

public interface Subject {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
