package org.plexian.pendulum;

public class TileMaterialKey extends Tile {

	@Override
	public int getId() {
		return 1001;
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
