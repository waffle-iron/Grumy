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

import org.plexian.grumy.Game;
import org.plexian.grumy.math.Vector2d;

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
