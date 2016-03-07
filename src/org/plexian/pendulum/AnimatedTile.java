package org.plexian.pendulum;

public abstract class AnimatedTile extends Tile {
	protected int stage;

	/**
	 * Calculates the next stage of the tile.
	 * @param stage The current stage of the tile.
	 * @return The id of the next stage's tile.
	 */
	public abstract int animate(int stage);
	public abstract int getMaxStage();
}
