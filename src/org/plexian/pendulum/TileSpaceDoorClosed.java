package org.plexian.pendulum;

import org.lwjgl.input.Keyboard;

public class TileSpaceDoorClosed extends AnimatedTile{

	@Override
	public int animate(int stage) {
		if(Keyboard.isKeyDown(Keyboard.KEY_PERIOD) && Game.world.entityManager.getEntity(0).getInventory().contains(Item.KEY)){
			Game.world.entityManager.getEntity(0).getInventory().remove(Item.KEY);;
			
			return Tile.SPACE_DOOR_ONE_THIRD.getId();
		}
		
		return this.getId();
	}

	@Override
	public int getId() {
		return 4;
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
		return true;
	}

	@Override
	public float[] getTextureCoordinates() {
		return new float[]{0, 2};
	}
	
	public int getMaxStage(){
		return 3;
	}
}
