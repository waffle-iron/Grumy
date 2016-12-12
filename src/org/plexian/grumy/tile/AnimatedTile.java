/**
 * Copyright 2016 The Plexian Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.plexian.grumy.tile;

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
