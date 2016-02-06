package com.stb.server.location;

import java.util.Date;
import java.util.Map;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;

public class LocationHandler implements MqttCallback {
	
	private static final Logger logger = LoggerFactory.getLogger(LocationHandler.class);
	
	private final Map<String, LatLong> locations;
	private final Gson gson = new Gson();
	
	public LocationHandler(Map<String, LatLong> locations) {
		this.locations = Preconditions.checkNotNull(locations);
	}

	@Override
	public void connectionLost(Throwable cause) {
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {

		logger.debug("{}:{}:{}", new Object[] {new Date(), topic, message});

		String payload = new String(message.getPayload());
		LocationUpdate locationUpdate = gson.fromJson(payload, LocationUpdate.class);
		String user = locationUpdate.getEmailAddress();
		double latitude = locationUpdate.getLatitude();
		double longtitude = locationUpdate.getLongtitude();
		locations.put(user, new LatLong(latitude, longtitude));
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
	}
}
