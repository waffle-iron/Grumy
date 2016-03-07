package org.plexian.pendulum;

public class ItemKey extends Item {

	@Override
	public int getId() {
		return 256;
	}

	@Override
	public String getName() {
		return "key";
	}

	@Override
	public float[] getTextureCoordinates() {
		return new float[]{1, 4};
	}
}
