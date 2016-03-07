package org.plexian.pendulum;

public class TileSpaceDoorOneThird extends AnimatedTile {

	@Override
	public int animate(int stage) {
		if(stage == 2){
			return Tile.SPACE_DOOR_TWO_THIRDS.getId();
		}
		
		return this.getId();
	}

	@Override
	public int getId() {
		return 5;
	}

	@Override
	public boolean isTransparent() {
		return false;
	}

	@Override
	public boolean isAnimated() {
		return true;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public float[] getTextureCoordinates() {
		return new float[]{1, 2};
	}

	@Override
	public int getMaxStage() {
		return 3;
	}

}
