package org.plexian.pendulum;

public class TileSpaceDoorTwoThirds extends AnimatedTile {

	@Override
	public int animate(int stage) {
		if(stage == 0){
			return Tile.SPACE_DOOR_CLOSED.getId();
		}else if(stage == 1){
			return Tile.SPACE_DOOR_ONE_THIRD.getId();
		}else if(stage == 2){
			return Tile.SPACE_DOOR_TWO_THIRDS.getId();
		}else{
			return Tile.SPACE_DOOR_OPEN.getId();
		}
	}

	@Override
	public int getId() {
		return 6;
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
		return new float[]{2, 2};
	}

	@Override
	public int getMaxStage() {
		return 3;
	}

}
