package org.plexian.pendulum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationSection extends Configuration {
	public Map<String, Object> dataMap = new HashMap<String, Object>();
	
	public ConfigurationSection(Map<String, Object> dataMap){
		this.dataMap.putAll(dataMap);
	}

	/**
	 * Gets the deepest section in the key. Note that you use an object's key, not a configuration section,
	 * otherwise you will get the second-deepest section.
	 * @param key The key to search through.
	 * @return The deepest section in the key.
	 */
	public ConfigurationSection getDeepestSection(String key){
		/**
		 * We split up the key into its components.
		 */
		String[] paths = key.split("\\.");
		
		/**
		 * We need two sections, one for the last one that we used, and one for the current section we are in.
		 */
		ConfigurationSection lastSection = this;
		ConfigurationSection currentSection = this;
		
		/**
		 * Here we loop through all the paths, moving downwards until we reach the second-to-last object.
		 */
		for(int i = 0; i < paths.length - 1; i++){
			currentSection = lastSection.getConfigurationSection(paths[i]);
		}
		
		return currentSection;
	}
	
	public int getInt(String key) {
		/**
		 * Basically if the user want's to fetch: users.walt.id, we need to descend all the way down to
		 * object 'walt' before looking up the object.
		 */
		if(key.contains(".")){
			String[] paths = key.split("\\.");
			
			/**
			 * We then fetch the object.
			 */
			return (Integer)getDeepestSection(key).getInt(paths[paths.length - 1]);
		}
		
		/**
		 * No .s in the path, so we just directly lookup the object.
		 */
		return (Integer)this.dataMap.get(key);
	}

	public double getDouble(String key) {
		/**
		 * Basically if the user want's to fetch: users.walt.id, we need to descend all the way down to
		 * object 'walt' before looking up the object.
		 */
		if(key.contains(".")){
			String[] paths = key.split("\\.");
			
			/**
			 * We then fetch the object.
			 */
			return (Double)getDeepestSection(key).getDouble(paths[paths.length - 1]);
		}
		
		/**
		 * No .s in the path, so we just directly lookup the object.
		 */
		return (Double)this.dataMap.get(key);
	}

	public float getFloat(String key) {
		/**
		 * Basically if the user want's to fetch: users.walt.id, we need to descend all the way down to
		 * object 'walt' before looking up the object.
		 */
		if(key.contains(".")){
			String[] paths = key.split("\\.");
			
			/**
			 * We then fetch the object.
			 */
			return (Float)getDeepestSection(key).getFloat(paths[paths.length - 1]);
		}
		
		/**
		 * No .s in the path, so we just directly lookup the object.
		 */
		return (Float)this.dataMap.get(key);
	}

	public boolean getBoolean(String key) {
		/**
		 * Basically if the user want's to fetch: users.walt.id, we need to descend all the way down to
		 * object 'walt' before looking up the object.
		 */
		if(key.contains(".")){
			String[] paths = key.split("\\.");
			
			/**
			 * We then fetch the object.
			 */
			return (boolean)getDeepestSection(key).getBoolean(paths[paths.length - 1]);
		}
		
		/**
		 * No .s in the path, so we just directly lookup the object.
		 */
		return (boolean)this.dataMap.get(key);
	}

	public byte getByte(String key) {
		/**
		 * Basically if the user want's to fetch: users.walt.id, we need to descend all the way down to
		 * object 'walt' before looking up the object.
		 */
		if(key.contains(".")){
			String[] paths = key.split("\\.");
			
			/**
			 * We then fetch the object.
			 */
			return (byte)getDeepestSection(key).getByte(paths[paths.length - 1]);
		}
		
		/**
		 * No .s in the path, so we just directly lookup the object.
		 */
		return (byte)this.dataMap.get(key);
	}

	public String getString(String key) {
		/**
		 * Basically if the user want's to fetch: users.walt.id, we need to descend all the way down to
		 * object 'walt' before looking up the object.
		 */
		if(key.contains(".")){
			String[] paths = key.split("\\.");
			
			/**
			 * We then fetch the object.
			 */
			if(paths.length == 0){
				return (String)getDeepestSection(key).getString(paths[paths.length]);
			}
			
			return (String)getDeepestSection(key).getString(paths[paths.length - 1]);
		}
		
		/**
		 * No .s in the path, so we just directly lookup the object.
		 */
		return (String)this.dataMap.get(key);
	}

	public ConfigurationSection getConfigurationSection(String key){
		/**
		 * Basically if the user want's to fetch: users.walt.id, we need to descend all the way down to
		 * object 'walt' before looking up the object.
		 */
		if(key.contains(".")){
			String[] paths = key.split("\\.");
			
			/**
			 * We then fetch the object.
			 */
			return new ConfigurationSection((getDeepestSection(key).getMap(paths[paths.length - 1]) != null ? getDeepestSection(key).getMap(paths[paths.length - 1]) : new HashMap<String, Object>()));
		}
		
		/**
		 * No .s in the path, so we just directly lookup the object.
		 */
		return new ConfigurationSection((Map<String, Object>)(this.dataMap.get(key) != null ? this.dataMap.get(key) : new HashMap<String, Object>()));
	}

	public Map<String, Object> getMap(String key) {
		/**
		 * Basically if the user want's to fetch: users.walt.id, we need to descend all the way down to
		 * object 'walt' before looking up the object.
		 */
		if(key.contains(".")){
			String[] paths = key.split("\\.");
			
			/**
			 * We then fetch the object.
			 */
			return getDeepestSection(key).getMap(paths[paths.length - 1]);
		}
		
		/**
		 * No .s in the path, so we just directly lookup the object.
		 */
		return (Map<String, Object>)this.dataMap.get(key);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		/**
		 * Basically if the user want's to put: users.walt.id, we need to descend all the way down to
		 * object 'walt' before adding the object.
		 */
		if(key.contains(".")){
			String[] paths = key.split("\\.");
			
			/**
			 * We then fetch the object.
			 */
			getDeepestSection(key).put(paths[paths.length - 1], value);
		}
		
		/**
		 * No .s in the path, so we just directly lookup the object.
		 */
		this.dataMap.put(key, value);
	}

	public void putHashMap(String key, HashMap<String, Object> map) {
		/**
		 * Basically if the user want's to put: users.walt.id, we need to descend all the way down to
		 * object 'walt' before adding the object.
		 */
		if(key.contains(".")){
			String[] paths = key.split("\\.");
			
			/**
			 * We then fetch the object.
			 */
			getDeepestSection(key).dataMap.putAll(map);
		}
		
		/**
		 * No .s in the path, so we just directly lookup the object.
		 */
		this.dataMap.putAll(map);
	}
	
	public List<Object> getList(String key){
		return (List<Object>)this.dataMap.get(key);
	}
}