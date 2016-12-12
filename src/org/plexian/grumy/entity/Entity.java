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
import org.plexian.grumy.opengl.ShaderProgram;
import org.plexian.grumy.world.Location;
import org.plexian.grumy.world.World;

/**
 * Provides the methods and fields for creating an in-game entity.
 * 
 * @author walt
 * @category Entity
 */
public abstract class Entity {
    /**
     * This stores the id of the last entity created, allowing us to assign ids
     * in incrementing order.
     */
    private static int lastId = -1;

    /**
     * This stores the velocity of the entity's movement.
     */
    protected Vector2d velocity;

    /**
     * This stores the entity's size.
     */
    protected Vector2d size;

    /**
     * This stores the entity's location.
     */
    protected Location location;

    /**
     * This stores the entity's old location, usefull for tracking movement.
     */
    protected Location oldLocation;

    /**
     * This is the Axil-Aligned-Bounding-Box for the entity.
     */
    protected AABB aabb;

    /**
     * This is the shader program for the entity.
     */
    protected ShaderProgram program;

    /**
     * This is the id for the entity.
     */
    protected int id;

    /**
     * This is the inventory for the entity.
     */
    protected Inventory inventory;

    /**
     * Update the entity with delta speed $delta
     * 
     * @param delta
     *            The delta speed.
     */
    public abstract void update(double delta);

    /**
     * Render the entity.
     */
    public abstract void render();

    /**
     * Create an entity.
     * 
     * @param shader
     *            The shader to use for this entity.
     * @param w
     *            The world this entity is located in.
     * @param x
     *            The x-coordinate this entity is located at.
     * @param y
     *            The y-coordinate this entity is located at.
     * @param sizeX
     *            The size of the entity on the x-axis.
     * @param sizeY
     *            The size of the entity on the y-axis.
     */
    public Entity(Shader shader, World w, double x, double y, double sizeX, double sizeY) {
        this.location = new Location(w, x, y);
        this.oldLocation = new Location(w, 0, 0);
        this.aabb = new AABB(new Vector2d(x, y), new Vector2d(sizeX, sizeY));
        this.velocity = Vector2d.ZERO;
        this.program = new ShaderProgram(shader.getvShader(), shader.getfShader());
        this.size = new Vector2d(sizeX, sizeY);

        Entity.lastId++;
        this.id = Entity.lastId;
    }

    /**
     * Destroy the entity.
     */
    public void dispose() {
        this.program.dispose();
    }

    /**
     * Get the location of this entity.
     * 
     * @return The location of this entity.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Get the movement velocity of the entity.
     * 
     * @return The movement velocity of this entity.
     */
    public Vector2d getVelocity() {
        return this.velocity;
    }

    /**
     * Move this entity to a new location.
     * 
     * @param newLocation
     *            The location to move the entity to.
     */
    public void move(Location newLocation) {
        this.oldLocation = this.location;
        this.location = newLocation;
        this.aabb.move(location.getX(), location.getY());
        ;
    }

    /**
     * Get the id of this entity.
     * 
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * Add a value to the entity's velocity.
     * 
     * @param x
     *            The X-velocity to set.
     * @param y
     *            The Y-velocity to set.
     */
    public void addVelocity(double x, double y, double delta) {
        this.velocity.add(x / delta, y / delta);
    }

    /**
     * Get the size of this entity.
     * 
     * @return The size of this entity.
     */
    public Vector2d getSize() {
        return size;
    }

    /**
     * Get the inventory of this entity.
     * 
     * @return The inventory of this entity.
     */
    public Inventory getInventory() {
        return this.inventory;
    }
}
