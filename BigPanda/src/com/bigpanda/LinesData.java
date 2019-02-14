package com.bigpanda;

import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.JsonObject;

public class LinesData {

	private ConcurrentHashMap<String,Integer> events = new ConcurrentHashMap<String,Integer>();
	private ConcurrentHashMap<String,Integer> words = new ConcurrentHashMap<String,Integer>();
	
	private static LinesData linesData = null;
	private LinesData() {};
	
	public static synchronized LinesData getInstance() {
		if ( linesData == null ) {
			linesData = new LinesData();
		}
		return linesData;
	}
	
	
	public int getEventCount(String event) {
		return events.computeIfAbsent(event, k -> 0);
	}
	
	public int getWordCount(String word) {
		return words.computeIfAbsent(word, k -> 0);
	}
	
	private void addEventCount(String event) {
		
		events.computeIfPresent(event, (key,val) -> 
		{
			if ( val == null ) {
				return 1;
			}
			return val+1;			
		});
		
		
		
	}
	private void addWordCount(String word) {
		words.computeIfPresent(word, (key,val) -> 
		{
			if ( val == null ) {
				return 1;
			}
			return val+1;			
		});
		
		
	}
	
	public void handle(JsonObject obj) {
		if ( obj.get("event_type") != null && obj.get("data") !=null ) {
			addEventCount(obj.get("event_type").getAsString());
			addWordCount(obj.get("data").getAsString());
		}
	}
	
	
	
}
