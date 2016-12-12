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

public class TileAnti extends Tile{

	@Override
	public int getId() {
		return 901;
	}

	@Override
	public boolean isTransparent() {
		return false;
	}

	@Override
	public float[] getTextureCoordinates() {
		return new float[]{4, 3};
	}

	@Override
	public boolean isAnimated() {
		return false;
	}

	@Override
	public boolean isSolid() {
		return false;
	}
}
