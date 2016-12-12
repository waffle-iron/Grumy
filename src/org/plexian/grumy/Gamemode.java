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
package org.plexian.grumy;

public enum Gamemode {
    NORMAL(0), 
    LEVEL_EDITOR(1);

    int id;

    /**
     * Create a new gamemode. 
     * @param id The id for the gamemode. Must be unique
     */
    Gamemode(int id) {
        this.id = id;
    }

    /**
     * Get the ID of the gamemode
     * @return The ID of the gamemode.
     */
    public int getId() {
        return id;
    }

    /**
     * Get the gamemode with the given ID.
     * 
     * @param id The ID of the gamemode
     * @return The gamemode with that ID, or Gamemode.NORMAL if there isn't one.
     */
    public static Gamemode getById(int id) {
        for (Gamemode gm : Gamemode.values()) {
            if (gm.getId() == id) {
                return gm;
            }
        }

        return Gamemode.NORMAL;
    }
}
