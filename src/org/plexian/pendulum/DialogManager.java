package org.plexian.pendulum;

import java.util.Collection;
import java.util.HashMap;

/**
 * Manages the dialog for the world, allows for the addition and deletion of in-game dialog.
 * @author walt
 */
public class DialogManager {
	/**
	 * This map stores all the dialog in a string-converted location form.
	 */
	private HashMap<String, Dialog> dialogMap;
	
	/**
	 * This is the world that we are rendering to.
	 */
	private World world;
	
	/**
	 * Create a new DialogManager for world $world.
	 * @param world The world that we are using this dialog manager for.
	 */
	public DialogManager(World world){
		this.world = world;
		dialogMap = new HashMap<String, Dialog>();
	}
	
	/**
	 * Add a dialog object to the world.
	 * @param dialog The dialog to add.
	 */
	public void addDialog(Dialog dialog){
		this.dialogMap.put(dialog.getLocation().toString(), dialog);
	}
	
	/**
	 * Construct and add a dialog object to the world. 
	 * @param location The location of this dialog.
	 * @param message The message of this dialog.
	 * @param color The color of the message of this dialog.
	 */
	public void addDialog(Location location, String message, RGBA color){
		this.dialogMap.put(location.toString(), new Dialog(location, message, color));
	}
	
	/**
	 * Remove the first instance of dialog at location $location.
	 * @param location The location of the dialog to remove.
	 */
	public void removeDialog(Location location){
		this.dialogMap.remove(location.toString());
	}
	
	/**
	 * Get all the dialog in the world.
	 * @return A collection of all the dialog in the world.
	 */
	public Collection<Dialog> getAllDialog(){
		return dialogMap.values();
	}
	
	/**
	 * Render all the dialog.
	 */
	public void render(){
		for(Dialog d : dialogMap.values()){
			d.render();
		}
	}
	
	/**
	 * Delete all the dialog.
	 */
	public void dispose(){
		for(Dialog d : dialogMap.values()){
		}
		
		dialogMap.clear();
	}
}
