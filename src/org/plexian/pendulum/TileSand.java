package org.plexian.pendulum;

public class TileSand extends Tile{

	@Override
	public int getId() {
		return 12;
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
		return new float[]{2, 3};
	}

}
