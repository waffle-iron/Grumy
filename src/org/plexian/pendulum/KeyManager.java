package org.plexian.pendulum;

import java.util.Collection;
import java.util.HashMap;

public class KeyManager {
	private World world;
	
	/**
	 * This stores all the in-game keys.
	 * $key = String = Location.
	 * $value = Tile ID to replace key with when picked up.
	 */
	private HashMap<String, Integer> keys;
	
	/**
	 * 
	 */
	public KeyManager(World w){
		this.world = w;
		this.keys = new HashMap<String, Integer>();
	}
	
	/**
	 * 
	 * @param location
	 * @param replaceId
	 */
	public void addKey(Location location, int replaceId){
		Game.LOG.info("Location: " + location.toString());
		this.keys.put(location.toString(), replaceId);
	}
	
	public void update(){
		for(String s : this.keys.keySet()){
			Game.world.setTile(Location.parseLocation(s).getX(), Location.parseLocation(s).getY(), Tile.KEY_PICKUP.getId());
		}
	}
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	public boolean checkForPickup(Location location){
		try{
			int tile = this.keys.get(location.toString());
			
			Game.world.setTile(location.getX(), location.getY(), tile);
			this.keys.remove(location.toString());
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getMap(){
		return this.keys;
	}
	
	/**
	 * 
	 * @return
	 */
	public Collection<String> getLocations(){
		return this.keys.keySet();
	}
	
	/**
	 * 
	 * @return
	 */
	public Collection<Integer> getReplaceTiles(){
		return this.keys.values();
	}
}
