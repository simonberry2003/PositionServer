package com.stb.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.stb.server.guice.ServerModule;

public class Server {

	private static Logger logger = LoggerFactory.getLogger(Server.class);
	
	private AtomicBoolean restart = new AtomicBoolean(true);
	private ExecutorService executor = Executors.newCachedThreadPool();
	
	public static void main(String[] args) throws Exception {
		Injector injector = Guice.createInjector(new ServerModule());
		Server server = injector.getInstance(Server.class);
		server.run();
	}

	private void run() throws Exception {
		logger.debug("Starting Server");
		executor.submit(new LocationUpdateCallable());
		while (true) {
			Thread.sleep(1000);
		}
	}

	public void restart() {
		restart.set(true); 
	}
}
