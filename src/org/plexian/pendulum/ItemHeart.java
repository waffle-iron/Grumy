package org.plexian.pendulum;

public class ItemHeart extends Item {

	@Override
	public int getId() {
		return 258;
	}

	@Override
	public String getName() {
		return "heart";
	}

	@Override
	public float[] getTextureCoordinates() {
		return new float[]{0, 5};
	}
}
