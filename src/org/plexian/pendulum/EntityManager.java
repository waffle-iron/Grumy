package org.plexian.pendulum;

import java.util.Collection;
import java.util.HashMap;

/**
 * Manages all the entities in a world.
 * @author walt
 *
 */
public class EntityManager {
	private HashMap<Integer, Entity> entityMap;
	private World world;
	
	/**
	 * 
	 */
	public EntityManager(World world){
		this.entityMap = new HashMap<Integer, Entity>();
		this.world = world;
	}
	
	public void render(){
		for(Entity e : entityMap.values()){
			if(e instanceof Player){
				continue;
			}
			
			e.render();
		}
	}
	
	public void update(double delta){
		for(Entity e : entityMap.values()){
			e.update(delta);
		}
	}
	
	public void dispose(){
		for(Entity e : entityMap.values()){
		//	world.worldData.addEntiy(e.getLocation().toString(), e.getId());
		}
		
		for(Entity e : entityMap.values()){
			e.dispose();
		}
	}
	
	/**
	 * 
	 * @param e
	 */
	public void addEntity(Entity e){
		entityMap.put(e.getId(), e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void removeEntity(Entity e){
		entityMap.remove(e.getId());

	}
	
	/**
	 * 
	 * @param id
	 */
	public void removeEntity(int id){
		entityMap.remove(id);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Entity getEntity(int id){
		return entityMap.get(id);
	}
	
	public Entity getEntity(Location l){
		for(Entity e : entityMap.values()){
			if(e.getLocation().getWorld() == l.getWorld() && e.getLocation().getX() == l.getX() && e.getLocation().getY() == l.getY()){
				return e;
			}
		}
		
		return null;
	}
	
	public Collection<Entity> getEntities(){
		return this.entityMap.values();
	}
}
