package com.antyron.sensors.listeners;

import com.antyron.sensors.objects.SensorData;
import com.antyron.services.HTTPService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author atyron
 */

public class DHTListener implements SensorListener {
    private static final Logger logger = Logger.getLogger(DHTListener.class);

    private HTTPService sendService;
    private String storage;

    public DHTListener(String storage, HTTPService sendService) {
        this.storage = storage;
        this.sendService = sendService;
    }

    @Override
    public void notifyData(SensorData data) {
        try {
            sendService.sendPost(data.getData(), storage);
        } catch (IOException e) {
            logger.error("Sending data error", e);
        }
    }
}
