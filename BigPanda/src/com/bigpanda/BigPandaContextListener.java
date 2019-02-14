package com.bigpanda;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BigPandaContextListener implements ServletContextListener {
	private final ExecutorService executorService = Executors.newSingleThreadExecutor();
	private MyProducer myObserverable = new MyProducer();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		executorService.shutdown();
		myObserverable.shutProcess();
	}

	// Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		executorService.execute(myObserverable);

	}

}
