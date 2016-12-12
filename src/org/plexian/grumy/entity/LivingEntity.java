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
package org.plexian.grumy.entity;

import org.plexian.grumy.inventory.Inventory;
import org.plexian.grumy.math.Vector2d;
import org.plexian.grumy.opengl.Shader;
import org.plexian.grumy.world.Location;
import org.plexian.grumy.world.World;

/**
 * The class for a living entity, that is an entity with life.
 * 
 * @author walt
 * @category entity
 */
public abstract class LivingEntity extends Entity {
    /**
     * The current life the entity has.
     */
    protected double life;

    /**
     * The maximum amount of life that the entity can have.
     */
    protected double maxLife;

    /**
     * Create a new instance of LivingEntity.
     * 
     * @param shader The shader for this entity.
     * @param location The location of the entity.
     * @param size The size of the entity stored as an ordered pair.
     */
    public LivingEntity(Shader shader, Location location, Vector2d size) {
        this(shader, 100, 100, location, size);
    }

    /**
     * Create a new instance of LivingEntity
     * 
     * @param inventory The entity's inventory.
     * @param shader The shader for the entity.
     * @param location The entity's location.
     * @param size The size of the entity's AABB as an ordered pair.
     */
    public LivingEntity(Inventory inventory, Shader shader, Location location, Vector2d size) {
        this(inventory, shader, 100, 100, location, size);
    }

    /**
     * Create a new instance of LivingEntity.
     * 
     * @param inventory The entity's inventory.
     * @param shader The shader for the entity.
     * @param life The current life for the entity.
     * @param maxLife The max amount of life the entity can have.
     * @param location The location of the entity.
     * @param size The size of the entity's AABB as an ordered pair.
     */
    public LivingEntity(Inventory inventory, Shader shader, double life, double maxLife, Location location,
            Vector2d size) {
        this(inventory, shader, location.getWorld(), location.getX(), location.getY(), size.getX(), size.getY(), life,
                maxLife);
    }

    /**
     * Create a new instance of LivingEntity at location with size, maxLife, and
     * life.
     * 
     * @param life  The current life of the entity.
     * @param maxLife The maximum life the entity can have.
     * @param location The location of the entity.
     * @param size The size of the entity as an ordered pair.
     */
    public LivingEntity(Shader shader, double life, double maxLife, Location location, Vector2d size) {
        this(null, shader, location.getWorld(), location.getX(), location.getY(), size.getX(), size.getY(), life,
                maxLife);
    }

    /**
     * Create a new instance of LivingEntity at location with sizeX, sizeY,
     * maxLife, and life.
     * 
     * @param w The world the entity is at.
     * @param x The X-coordinate the entity is at.
     * @param y The Y-coordinate the entity is at.
     * @param sizeX The size of the entity X-axis respective.
     * @param sizeY The size of the entity Y-axis respective.
     * @param life The current life of the entity.
     * @param maxLife The maximum life the entity can have.
     */
    public LivingEntity(Inventory inventory, Shader shader, World w, double x, double y, double sizeX, double sizeY,
            double life, double maxLife) {
        super(shader, w, x, y, sizeX, sizeY);

        this.life = life;
        this.maxLife = maxLife;

        if (inventory != null) {
            this.inventory = inventory;
        } else {
            this.inventory = new Inventory(10, this);
        }
    }

    /**
     * Check if entity is alive.
     * 
     * @return True if entity is alive, false if not.
     */
    public boolean alive() {
        return (life > 0);
    }

    /**
     * Check if the entity has full life.
     * 
     * @return True if the entity has full life, false if not.
     */
    public boolean hasFullLife() {
        return (life == maxLife);
    }

    /**
     * Get the current life of the entity.
     * 
     * @return The current life of the entity.
     */
    public double getLife() {
        return life;
    }

    /**
     * Get the maximum life of the entity.
     * 
     * @return The maximum life of the entity.
     */
    public double getMaxLife() {
        return maxLife;
    }
}