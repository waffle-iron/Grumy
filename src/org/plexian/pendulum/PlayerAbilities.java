package org.plexian.pendulum;

import java.util.HashMap;

/**
 * 
 * @author walt
 *
 */
public class PlayerAbilities {
	private HashMap<String, Object> abilities;
	
	/**
	 * 
	 * @param canFly
	 * @param canBuildInstantly
	 * @param canMineAll
	 * @param walkSpeed
	 * @param flySpeed
	 */
	public PlayerAbilities(boolean canFly, boolean canBuildInstantly, boolean canMineAll, float walkSpeed, float flySpeed){
		this.abilities.put("canFly", canFly);
		this.abilities.put("canBuildInstantly", canBuildInstantly);
		this.abilities.put("canMineAll", canMineAll);
		this.abilities.put("walkSpeed", walkSpeed);
		this.abilities.put("flySpeed", flySpeed);
	}
	
	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void addAbility(String name, Object value){
		this.abilities.put(name, value);
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Object getAbility(String name){
		return this.abilities.get(name);
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMap<String, Object> getAbilities(){
		return abilities;
	}
	
	/**
	 * 
	 * @param map
	 */
	public void putAbilities(HashMap<String, Object> map){
		this.abilities.putAll(map);
	}
}