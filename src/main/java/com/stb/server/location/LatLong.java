package com.stb.server.location;

public class LatLong {
	
	public final double latitude;
	public final double longtitude;
	
	public LatLong(double latitude, double longtitude) {
		this.latitude = latitude;
		this.longtitude = longtitude;
	}
	
	@Override
	public String toString() {
		return longtitude + ":" + latitude;
	}
}
