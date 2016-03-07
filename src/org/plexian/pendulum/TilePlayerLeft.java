package org.plexian.pendulum;

public class TilePlayerLeft extends Tile {

	@Override
	public int getId() {
		return 901;
	}

	@Override
	public boolean isTransparent() {
		return false;
	}

	@Override
	public boolean isAnimated() {
		return false;
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public float[] getTextureCoordinates() {
		return new float[]{0, 1};
	}

}
