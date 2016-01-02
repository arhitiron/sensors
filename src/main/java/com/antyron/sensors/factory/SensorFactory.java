package com.antyron.sensors.factory;

import com.antyron.sensors.DHT;
import com.antyron.sensors.Sensor;
import com.antyron.sensors.listeners.DHTListener;
import com.antyron.services.HTTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author atyron
 */

@Component
public class SensorFactory {
    @Autowired
    private HTTPService sendService;
    @Value("${app.sensor.dht.exec}")
    private String exec;
    @Value("${app.sensor.dht.type}")
    private String type;
    @Value("${app.sensor.dht.pin}")
    private int pin;
    @Value("${app.sensor.dht.storage}")
    private String dhtStorage;
    @Value("${app.sensor.handle.timeout}")
    private long timeOut;


    public Sensor getDHT() {
        Sensor dht = new DHT.DHTBuilder().addExec(exec).withType(type).andPin(pin).andTimeOut(timeOut).build();
        dht.addListener(new DHTListener(dhtStorage, sendService));
        return dht;
    }
}
