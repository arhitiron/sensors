package com.antyron.sensors.listeners;

import com.antyron.sensors.objects.SensorData;

/**
 * @author atyron
 */
public interface SensorListener {
    void notifyData(SensorData data);
}
