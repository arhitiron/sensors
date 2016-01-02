package com.antyron.services;

import com.antyron.sensors.Sensor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author antyron
 */
@Component
public class SensorManagerImpl implements SensorManager {
    private List<Sensor> sensors = new ArrayList<>();

    @Override
    public void add(Sensor sensor) {
        sensors.add(sensor);
    }

    @Override
    public void remove(Sensor sensor) {
        sensors.remove(sensor);
    }

    @Override
    public void handle() {
        sensors.forEach(Sensor::handle);
    }
}
