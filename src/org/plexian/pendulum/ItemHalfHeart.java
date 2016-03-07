package org.plexian.pendulum;

public class ItemHalfHeart extends Item {

	@Override
	public int getId() {
		return 259;
	}

	@Override
	public String getName() {
		return "half_heart";
	}

	@Override
	public float[] getTextureCoordinates() {
		return new float[]{1, 5};
	}
}
