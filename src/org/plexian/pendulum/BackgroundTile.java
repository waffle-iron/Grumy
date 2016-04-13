package org.plexian.pendulum;

import java.util.HashMap;

public abstract class BackgroundTile extends Tile {
	private HashMap<Integer, BackgroundTile> tileMap;
	
	@Override
	public boolean isSolid(){
		return false;
	}
}
