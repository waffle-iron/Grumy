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

import java.util.HashMap;

public abstract class Item {
    private static HashMap<Integer, Item> itemMap = new HashMap<Integer, Item>();
    public static Item AIR = new ItemAir();
    public static Item KEY = new ItemKey();
    public static Item HEART = new ItemHeart();
    public static Item HALF_HEART = new ItemHalfHeart();

    public abstract int getId();

    public abstract String getName();

    public abstract float[] getTextureCoordinates();

    static {
        itemMap.put(256, AIR);
        itemMap.put(257, KEY);
        itemMap.put(258, HEART);
        itemMap.put(259, HALF_HEART);
    };

    public static Item getItem(int id) {
        return itemMap.get(id);
    }
}
