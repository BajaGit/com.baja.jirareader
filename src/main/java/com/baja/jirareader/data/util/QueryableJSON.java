package com.baja.jirareader.data.util;

import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@FunctionalInterface
public interface QueryableJSON {
	
	JSONObject getJSON();
	
	default boolean isJSONpresent(){
		return getJSON() != null;
	}
	
	default boolean write(String path, Object value){
		Optional<Object> existing= query(path);
		boolean ret = false;
		String[] loc = path.split(":");
		JSONObject json = getJSON();
		
		if ( existing.isPresent()){
			for ( int i = 0; i < loc.length; i++){
				if ( i < loc.length - 1){
					json = (JSONObject) queryField(loc[i],json).get();
				} else {
					json.put(loc[i], value);
					ret = true;
				}
			}
		} else {
			System.out.println("not present");
			for ( int i = 0; i < loc.length; i++){
				Optional<Object> field = queryField(loc[i],json);
				if ( i < loc.length - 1 ){
					if ( field.isPresent()){
						json = (JSONObject) field.get();
					} else {
						JSONObject newobj = new JSONObject();
						json.put(loc[i], newobj);
						json = newobj;
					}
				} else {
					json.put(loc[i], value);
					ret = true;
				}
			}
		}
		
		
		return ret;
	}
	
	default Optional<Object> query(String query){
		JSONObject json = getJSON();
		String[] path = query.split(":");
		
		Object result = null;
		for ( int i = 0; i < path.length; i++){
			Optional<Object> field = queryField(path[i], json);
			if ( field.isPresent()){
				Object val = field.get();
				if ( i == path.length -1){
					result = val;
				} else if ( val instanceof JSONObject){
					json = (JSONObject) val;
				} else {
					System.out.println("could not find path[" + query + "] in Object!");
					break;
				}
			} else {
				break;
			}
		}
		
		return Optional.ofNullable(result);
	}
	
	default Optional<JSONObject> queryObj(String query){
		Optional<Object> res = query(query);
		return Optional.ofNullable(res.isPresent() ? (JSONObject) res.get() : null);
	}
	
	default Optional<JSONArray> queryArray(String query){
		Optional<Object> res = query(query);
		return Optional.ofNullable(res.isPresent() ? (JSONArray) res.get() : null);
	}

	default Optional<String> queryString(String query){
		Optional<Object> res = query(query);
		return Optional.ofNullable(res.isPresent() ? (String) res.get() : null);
	}
	
	default Optional<Long> queryLong(String query){
		Optional<Object> res = query(query);
		return Optional.ofNullable(res.isPresent() ? (Long) res.get() : null);
	}
	
	default Optional<Integer> queryInteger(String query){
		Optional<Object> res = query(query);
		return Optional.ofNullable(res.isPresent() ? (Integer) res.get() : null);
	}
	
	
	default Optional<Object> queryField(String fieldName, JSONObject json){
		return Optional.ofNullable(json.get(fieldName));
	}
}
