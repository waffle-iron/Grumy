package org.plexian.pendulum;

/**
 * Provides the class for managing in-game text dialog
 * @author walt
 */
public class Dialog {
	/**
	 * This stores the location of the dialog object.
	 */
	private Location location;
	
	/**
	 * This stores the message of the dialog.
	 */
	private String message;
	
	/**
	 * This stores the color of the dialog.
	 */
	private RGBA color;
	
	/**
	 * Create a new instance of Dialog at location $location, with message $message, and color $color.
	 * @param location The location of the dialog.
	 * @param message The message of the dialog.
	 * @param color The color of the dialog message.
	 */
	public Dialog(Location location, String message, RGBA color){
		this.location = location;
		this.message = message;
		this.color = color;
	}
	
	/**
	 * Get the location of this dialog
	 * @return The location of this dialog.
	 */
	public Location getLocation(){
		return location;
	}
	
	/**
	 * Get the message of this dialog.
	 * @return The message of this dialog.
	 */
	public String getMessage(){
		return message;
	}
	
	/**
	 * Render the dialog onto the game.
	 */
	public void render(){
		TextRenderer.drawInGameText(this.message, this.getLocation().getX(), this.getLocation().getY(), this.color);
	}
}
