package com.antyron.sensors.objects;

import java.util.Map;

/**
 * @author atyron
 */
public class SensorData {
    private Map<String, String> data;

    public SensorData(Map<String, String> data){
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
