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
