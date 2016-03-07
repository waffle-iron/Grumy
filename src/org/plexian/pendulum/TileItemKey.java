package org.plexian.pendulum;

public class TileItemKey extends Tile{

	@Override
	public int getId() {
		return 1002;
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
		return new float[]{1, 4};
	}

}
