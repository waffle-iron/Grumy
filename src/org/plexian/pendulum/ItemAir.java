package org.plexian.pendulum;

public class ItemAir extends Item {

	@Override
	public int getId() {
		return 256;
	}

	@Override
	public String getName() {
		return "air";
	}

	@Override
	public float[] getTextureCoordinates() {
		return new float[]{-1, -1};
	}

}
