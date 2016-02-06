package com.stb.server.location;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;

public class LocationUpdateMessage {
    
	private final String emailAddress;
	private final double latitude;
    private final double longtitude;

    public LocationUpdateMessage(String emailAddress, double latitude, double longtitude) {
        this.emailAddress = Preconditions.checkNotNull(emailAddress);
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

	public String getEmailAddress() {
		return emailAddress;
	}

    public double getLatitude() {
		return latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}
}
