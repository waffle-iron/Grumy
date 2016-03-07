package org.plexian.pendulum;

import java.util.Collection;
import java.util.HashMap;

public class DialogManager {
	private HashMap<String, Dialog> dialogMap;
	private World world;
	
	public DialogManager(World world){
		this.world = world;
		dialogMap = new HashMap<String, Dialog>();
	}
	
	public void addDialog(Dialog dialog){
		this.dialogMap.put(dialog.getLocation().toString(), dialog);
	}
	
	public void addDialog(Location location, String message, RGBA color){
		this.dialogMap.put(location.toString(), new Dialog(location, message, color));
	}
	
	public void removeDialog(Location location){
		this.dialogMap.remove(location.toString());
	}
	
	public Collection<Dialog> getAllDialog(){
		return dialogMap.values();
	}
	
	public void render(){
		for(Dialog d : dialogMap.values()){
			d.render();
		}
	}
	
	public void dispose(){
		for(Dialog d : dialogMap.values()){
		}
	}
}
