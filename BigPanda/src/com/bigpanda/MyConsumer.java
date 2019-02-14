package com.bigpanda;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class MyConsumer {

	private Gson g = new Gson();
	private static MyConsumer instance =  null;
	private MyConsumer() {}
	
	public static synchronized MyConsumer getInstance() {
		if ( instance == null ) {
			instance = new MyConsumer();
		}
		return instance;
	}
	
	public void accept(String line) {
		
		
		JsonObject lineobj = null;
		try {
			lineobj = g.fromJson(line, JsonObject.class);
			LinesData.getInstance().handle(lineobj);
			//System.out.println(lineobj);
		} catch (JsonSyntaxException e) {
			System.err.println("error parsing --> " + line);
		}
		
	}

}
