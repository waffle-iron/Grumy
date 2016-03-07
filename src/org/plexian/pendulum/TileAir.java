/**
 * Copyright Plexian Studios, 2014-2016. All Rights Reserved.
 * Released under Plexian License (see plexian.org/license).
 * If unable to obtain license, please email support@plexian.org.
 */
package org.plexian.pendulum;

public class TileAir extends Tile{

	@Override
	public boolean isTransparent(){
		return true;
	}
	
	@Override
	public boolean isAnimated(){
		return false;
	}
	
	@Override
	public boolean isSolid(){
		return false;
	}
	
	@Override
	public int getId() {
		return 0;
	}
	
	@Override
	public float[] getTextureCoordinates() {
		return new float[]{-100, -100};
	}
}
