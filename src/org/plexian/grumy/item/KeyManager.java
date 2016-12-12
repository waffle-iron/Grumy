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
package org.plexian.grumy.item;

import java.util.Collection;
import java.util.HashMap;

import org.plexian.grumy.Game;
import org.plexian.grumy.tile.Tile;
import org.plexian.grumy.world.Location;
import org.plexian.grumy.world.World;

public class KeyManager {
    private World world;

    /**
     * This stores all the in-game keys. $key = String = Location. $value = Tile
     * ID to replace key with when picked up.
     */
    private HashMap<String, Integer> keys;

    /**
     * 
     */
    public KeyManager(World w) {
        this.world = w;
        this.keys = new HashMap<String, Integer>();
    }

    /**
     * 
     * @param location
     * @param replaceId
     */
    public void addKey(Location location, int replaceId) {
        Game.LOG.info("Location: " + location.toString());
        this.keys.put(location.toString(), replaceId);
    }

    public void update() {
        for (String s : this.keys.keySet()) {
            Game.world.setTile(Location.parseLocation(s).getX(), Location.parseLocation(s).getY(),
                    Tile.KEY_PICKUP.getId());
        }
    }

    /**
     * 
     * @param location
     * @return
     */
    public boolean checkForPickup(Location location) {
        try {
            int tile = this.keys.get(location.toString());

            Game.world.setTile(location.getX(), location.getY(), tile);
            this.keys.remove(location.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 
     * @return
     */
    public HashMap<String, Integer> getMap() {
        return this.keys;
    }

    /**
     * 
     * @return
     */
    public Collection<String> getLocations() {
        return this.keys.keySet();
    }

    /**
     * 
     * @return
     */
    public Collection<Integer> getReplaceTiles() {
        return this.keys.values();
    }
}
