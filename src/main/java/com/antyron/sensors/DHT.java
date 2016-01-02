package com.antyron.sensors;

import com.antyron.sensors.listeners.SensorListener;
import com.antyron.sensors.objects.SensorData;
import com.pi4j.component.ObserveableComponentBase;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author atyron
 */
public class DHT extends ObserveableComponentBase implements Sensor {
    private static final Logger logger = Logger.getLogger(DHT.class);
    private static final String TEMPERATURE = "temperature";
    private static final String HUMIDITY = "humidity";
    private static final String SPLIT_CHAR = " ";

    private SensorListener listener;
    private String exec;
    private String type;
    private int pin;
    private long timeOut;
    private Thread processor;

    public DHT(DHTBuilder builder) {
        this.exec = builder.getExec();
        this.type = builder.getType();
        this.pin = builder.getPin();
        this.timeOut = builder.getTimeOut();
    }

    private String getSensorData() throws IOException {
        List<String> commands = new ArrayList<>();
        commands.add(exec);
        commands.add(type);
        commands.add("" + pin);

        Process process = new ProcessBuilder(commands).start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String readLine = bufferedReader.readLine();
        if (readLine == null) {
            process.destroy();
            throw new RuntimeException("Error, AdafruitDHT could not start");
        }
        process.destroy();

        return readLine;
    }

    public Map<String, String> getData() throws IOException {
        String[] sData = getSensorData().split(SPLIT_CHAR);
        Map<String, String> result = new HashMap<>();
        result.put(TEMPERATURE, sData[0]);
        result.put(HUMIDITY, sData[1]);
        return result;
    }

    @Override
    public void handle() {
        processor = new Thread(new SensorProcessor());
        processor.start();
    }

    @Override
    public void addListener(SensorListener listener) {
        this.listener = listener;
    }

    public static class DHTBuilder {
        private String exec;
        private String type;
        private int pin;
        private long timeOut;

        public String getExec() {
            return exec;
        }

        public DHTBuilder addExec(String exec) {
            this.exec = exec;
            return this;
        }

        public String getType() {
            return type;
        }

        public DHTBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public int getPin() {
            return pin;
        }

        public DHTBuilder andPin(int pin) {
            this.pin = pin;
            return this;
        }

        public long getTimeOut() {
            return timeOut;
        }

        public DHTBuilder andTimeOut(long timeOut) {
            this.timeOut = timeOut;
            return this;
        }

        public DHT build() {
            return new DHT(this);
        }
    }

    private class SensorProcessor implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    logger.info("NotifyData");
                    listener.notifyData(new SensorData(getData()));
                    Thread.sleep(timeOut);
                } catch (InterruptedException ignored) {
                } catch (IOException e) {
                    logger.error("Get data error", e);
                }
            }
        }
    }
}