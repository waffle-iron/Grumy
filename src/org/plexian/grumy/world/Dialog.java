/**
 * Copyright 2016 The Plexian Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.plexian.grumy.world;

import org.plexian.grumy.opengl.RGBA;
import org.plexian.grumy.opengl.TextRenderer;

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
