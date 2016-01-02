package com.antyron.services;

import com.antyron.sensors.Sensor;

/**
 * @author atyron
 */
public interface SensorManager {
    void add(Sensor sensor);

    void remove(Sensor sensor);

    void handle();
}
