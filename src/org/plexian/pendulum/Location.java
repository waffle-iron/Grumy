package org.plexian.pendulum;

public class Location {
	private World world;
	private double x;
	private double y;
	private double rotX;
	private double rotY;

	public static Location parseLocation(String location){
		String worldName = location.split(",")[0];
		double xPos = Double.parseDouble(location.split(",")[1]);
		double yPos = Double.parseDouble(location.split(",")[2]);
		
		return new Location(Game.getWorld(worldName), xPos, yPos);
	}

	public Location(World w, double x, double y){
		this(w, x, y, 0, 0);
	}
	
	public Location(World w, double x, double y, double rotX, double rotY){
		this.world = w;
		this.x = x;
		this.y = y;
		this.rotX = rotX;
		this.rotY = rotY;
	}

	public World getWorld(){
		return world;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void add(double x, double y){
		this.x += x;
		this.y += y;
	}
	
	public Vector2d toVector2d(){
		return new Vector2d(x, y);
	}
	
	@Override
	public String toString(){
		return new String(this.world.getName() + "," + this.getX() + "," + this.getY());
	}
}
