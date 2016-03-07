package org.plexian.pendulum;

import org.lwjgl.input.Keyboard;

public class TileSpaceDoorOpen extends AnimatedTile {

	@Override
	public int animate(int stage) {
		if(stage == 3){
			return Tile.SPACE_DOOR_OPEN.getId();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SLASH)){
			return Tile.SPACE_DOOR_CLOSED.getId();
		}
		
		return this.getId();
	}

	@Override
	public int getId() {
		return 7;
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
		return new float[]{3, 2};
	}

	@Override
	public int getMaxStage() {
		return 3;
	}

}
