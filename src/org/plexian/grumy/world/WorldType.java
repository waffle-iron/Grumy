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

public enum WorldType {
	CLASSIC(1),
	SAND(2),
	AIR(3),
	SPACE(4);
	
	private int id;
	
	WorldType(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}

	public static WorldType getType(String s){
		switch(s){
		case "classic":
			return WorldType.CLASSIC;
		case "sand":
			return WorldType.SAND;
		case "air":
			return WorldType.AIR;
		case "space":
			return WorldType.SPACE;
		default: return WorldType.CLASSIC;
		}
	}
	
	public String toString(){
		return name().toLowerCase();
	}
}
