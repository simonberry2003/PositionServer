package com.stb.server.guice;

import com.google.inject.AbstractModule;
import com.stb.server.location.LocationDao;
import com.stb.server.location.LocationDaoImpl;

public class ServerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(LocationDao.class).to(LocationDaoImpl.class);
	}
}
