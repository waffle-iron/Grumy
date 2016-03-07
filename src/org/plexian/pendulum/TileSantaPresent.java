package org.plexian.pendulum;

public class TileSantaPresent extends Tile {

	@Override
	public int getId() {
		return 11;
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
		return Tile.KEY_PICKUP.getTextureCoordinates();
	}

}
