package org.plexian.pendulum;

/**
 * Provides a way to animate tiles.
 * @author walt
 * @category Tile
 */
public abstract class AnimatedTile extends Tile {
	/**
	 * The current stage of the animation with each stage being a frame.
	 */
	protected int stage;

	/**
	 * Calculates the next stage of the tile.
	 * @param stage The current stage of the tile.
	 * @return The id of the next stage's tile.
	 */
	public abstract int animate(int stage);
	
	/**
	 * Get the last stage number for this tile..
	 * @return The last stage number for this tile.
	 */
	public abstract int getMaxStage();
}
