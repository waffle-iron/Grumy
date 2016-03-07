/**
 * Copyright Plexian Studios, 2014-2016. All Rights Reserved.
 * Released under Plexian License (see plexian.org/license).
 * If unable to obtain license, please email support@plexian.org.
 */
package org.plexian.pendulum;

public class TilePlayerRight extends Tile {

	@Override
	public boolean isTransparent(){
		return false;
	}
	
	@Override
	public int getId() {
		return 900;
	}

	@Override
	public boolean isSolid(){
		return false;
	}
	
	@Override
	public float[] getTextureCoordinates() {
		return new float[]{1, 1};
	}

	@Override
	public boolean isAnimated() {
		return false;
	}
}
