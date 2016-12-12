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
package org.plexian.grumy.entity.player;

import java.util.HashMap;

/**
 * 
 * @author walt
 *
 */
public class PlayerAbilities {
    private HashMap<String, Object> abilities;

    /**
     * 
     * @param canFly
     * @param canBuildInstantly
     * @param canMineAll
     * @param walkSpeed
     * @param flySpeed
     */
    public PlayerAbilities(boolean canFly, boolean canBuildInstantly, boolean canMineAll, float walkSpeed,
            float flySpeed) {
        this.abilities.put("canFly", canFly);
        this.abilities.put("canBuildInstantly", canBuildInstantly);
        this.abilities.put("canMineAll", canMineAll);
        this.abilities.put("walkSpeed", walkSpeed);
        this.abilities.put("flySpeed", flySpeed);
    }

    /**
     * 
     * @param name
     * @param value
     */
    public void addAbility(String name, Object value) {
        this.abilities.put(name, value);
    }

    /**
     * 
     * @param name
     * @return
     */
    public Object getAbility(String name) {
        return this.abilities.get(name);
    }

    /**
     * 
     * @return
     */
    public HashMap<String, Object> getAbilities() {
        return abilities;
    }

    /**
     * 
     * @param map
     */
    public void putAbilities(HashMap<String, Object> map) {
        this.abilities.putAll(map);
    }
}