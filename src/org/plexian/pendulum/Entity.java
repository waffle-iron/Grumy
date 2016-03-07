package org.plexian.pendulum;

import org.plexian.pendulum.Location;

public abstract class Entity {
	private static int lastId = -1;
	
	protected Vector2d velocity, size;
	protected Location location;
	protected Location oldLocation;
	protected AABB aabb;
	protected ShaderProgram program;
	protected int id;
	protected Inventory inventory;
	
	public abstract void update(double delta);
	public abstract void render();
	
	/**
	 * 
	 * @param shaderProgram
	 * @param w
	 * @param x
	 * @param y
	 * @param sizeX
	 * @param sizeY
	 */
	public Entity(Shader shader, World w, double x, double y, double sizeX, double sizeY){
		this.location = new Location(w, x, y);
		this.oldLocation = new Location(w, 0, 0);
		this.aabb = new AABB(new Vector2d(x, y), new Vector2d(sizeX, sizeY));
		this.velocity = Vector2d.ZERO;
		this.program = new ShaderProgram(shader.getvShader(), shader.getfShader());
		this.size = new Vector2d(sizeX, sizeY);
		
		Entity.lastId++;
		this.id = Entity.lastId;
	}

	public void dispose(){
		this.program.dispose();
	}
	
	/**
	 * 
	 * @return
	 */
	public Location getLocation(){
		return this.location;
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector2d getVelocity(){
		return this.velocity;
	}
	
	/**
	 * 
	 * @param newLocation
	 */
	public void move(Location newLocation){
		this.oldLocation = this.location;
		this.location = newLocation;
		this.aabb.move(location.getX(), location.getY());;
	}
	
	public int getId(){
		return this.id;
	}
	
	/**
	 * Add a value to the player's velocity.
	 * @param x The X-velocity to set.
	 * @param y The Y-velocity to set.
	 */
	public void addVelocity(double x, double y, double delta){
		this.velocity.add(x / delta, y / delta);
	}
	
	public Vector2d getSize(){
		return size;
	}
	
	public Inventory getInventory(){
		return this.inventory;
	}
}
