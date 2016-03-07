package org.plexian.pendulum;

public class TileAnti extends Tile{

	@Override
	public int getId() {
		return 901;
	}

	@Override
	public boolean isTransparent() {
		return false;
	}

	@Override
	public float[] getTextureCoordinates() {
		return new float[]{4, 3};
	}

	@Override
	public boolean isAnimated() {
		return false;
	}

	@Override
	public boolean isSolid() {
		return false;
	}
}
