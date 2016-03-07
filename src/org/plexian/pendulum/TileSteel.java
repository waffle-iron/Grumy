package org.plexian.pendulum;

public class TileSteel extends Tile {

	@Override
	public int getId() {
		return 3;
	}

	@Override
	public boolean isTransparent() {
		return false;
	}

	@Override
	public boolean isSolid(){
		return true;
	}
	
	@Override
	public boolean isAnimated() {
		return false;
	}

	@Override
	public float[] getTextureCoordinates() {
		return new float[]{0, 3};
	}

}
