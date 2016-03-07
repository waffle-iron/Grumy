package org.plexian.pendulum;

public class TileChainLeft extends Tile {

	@Override
	public int getId() {
		return 14;
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
		return false;
	}

	@Override
	public float[] getTextureCoordinates() {
		return new float[]{4, 3};
	}

}
