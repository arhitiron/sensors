package com.antyron.sensors;

import com.antyron.sensors.listeners.SensorListener;

/**
 * @author atyron
 */
public interface Sensor {
    void handle();

    void addListener(SensorListener listener);

}
