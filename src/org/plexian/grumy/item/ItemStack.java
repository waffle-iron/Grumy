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

import org.plexian.grumy.material.Material;

public class ItemStack {
    private int ammount;
    private Material material;
    private boolean canDrop;

    public ItemStack(Material m) {
        this(m, 1);
    }

    public ItemStack(Material m, int ammount) {
        this(m, ammount, true);
    }

    public ItemStack(Material m, int ammount, boolean canDrop) {
        this.material = m;
        this.ammount = ammount;
        this.canDrop = canDrop;
    }

    public int getAmmount() {
        return ammount;
    }

    public Material getMaterial() {
        return this.material;
    }

    public boolean canDrop() {
        return canDrop;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public void setMaterial(Material m) {
        this.material = m;
    }

    public void setCanDrop(boolean canDrop) {
        this.canDrop = canDrop;
    }
}
