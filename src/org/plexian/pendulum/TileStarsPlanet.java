package org.plexian.pendulum;

public class TileStarsPlanet extends Tile{

	@Override
	public int getId() {
		return 2;
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
	public boolean isSolid(){
		return false;
	}
	
	@Override
	public float[] getTextureCoordinates() {
		return new float[]{1, 0};
	}
}
