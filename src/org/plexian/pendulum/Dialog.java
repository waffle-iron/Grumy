package org.plexian.pendulum;

public class Dialog {
	private Location location;
	private String message;
	private RGBA color;
	
	public Dialog(Location location, String message, RGBA color){
		this.location = location;
		this.message = message;
		this.color = color;
	}
	
	public Location getLocation(){
		return location;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void render(){
		TextRenderer.drawInGameText(this.message, this.getLocation().getX(), this.getLocation().getY(), RGBA.WHITE);
	}
}
