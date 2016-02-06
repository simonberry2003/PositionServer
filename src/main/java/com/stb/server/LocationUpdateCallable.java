package com.stb.server;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stb.server.location.LatLong;
import com.stb.server.location.LocationHandler;

public class LocationUpdateCallable implements Callable<Void> {

	private static Logger logger = LoggerFactory.getLogger(LocationUpdateCallable.class);
	
	private boolean running = true;
	private MqttClient client;
	private Map<String, LatLong> locations = new ConcurrentHashMap<String, LatLong>();

	@Override
	public Void call() throws Exception {
		try {
			client = new MqttClient("tcp://simonberry2003.ddns.net:1883", MqttClient.generateClientId(), new MemoryPersistence());
			MqttConnectOptions options = new MqttConnectOptions();
			options.setCleanSession(true);
			client.connect(options);
			client.setCallback(new LocationHandler(locations));
			client.subscribe("position.update", 0);
			logger.info("Position updates started");
		} catch (Exception e) 			{
			logger.error("Error", e);	
		}
		while (running) {
			Thread.sleep(1000);
		}
		return null;
	}
}
