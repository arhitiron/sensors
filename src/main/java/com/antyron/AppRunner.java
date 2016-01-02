package com.antyron;

import com.antyron.sensors.factory.SensorFactory;
import com.antyron.services.SensorManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

/**
 * @author atyron
 */
@Component
@PropertySources({ @PropertySource("classpath:config/application.properties"), @PropertySource("file:conf/application.properties") })
public class AppRunner implements CommandLineRunner {

    @Autowired
    private SensorManagerImpl sensorManager;
    @Autowired
    private SensorFactory sensorFactory;

    @Override
    public void run(String... arg) throws Exception {
        sensorManager.add(sensorFactory.getDHT());
        sensorManager.handle();
    }
}