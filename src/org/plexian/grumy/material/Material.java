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
package org.plexian.grumy.material;

import org.plexian.grumy.tile.Tile;

public enum Material {
    AIR("air", Tile.AIR, 0), 
    KEY("key", Tile.ITEM_KEY, 1);

    private String name;
    private Tile displayTile;
    private int materialId;

    Material(String name, Tile displayTile, int materialId) {
        this.name = name;
        this.displayTile = displayTile;
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public Tile getDisplayTile() {
        return displayTile;
    }

    public int getMaterialId() {
        return this.materialId;
    }
}
