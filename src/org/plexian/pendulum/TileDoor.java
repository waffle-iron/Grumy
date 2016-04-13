package org.plexian.pendulum;

public class TileDoor extends Tile {

	@Override
	public int getId() {
		return 16;
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
		return new float[]{6,3};
	}

}
