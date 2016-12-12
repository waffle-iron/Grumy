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

import java.util.Collection;
import java.util.HashMap;

import org.plexian.grumy.entity.player.Player;
import org.plexian.grumy.world.Location;
import org.plexian.grumy.world.World;

/**
 * Manages all the entities in a world.
 * 
 * @author walt
 *
 */
public class EntityManager {
    private HashMap<Integer, Entity> entityMap;
    private World world;

    /**
     * Create a new EntityManager for the given world.
     * 
     * @param world The world this entity manager belongs too.
     */
    public EntityManager(World world) {
        this.entityMap = new HashMap<Integer, Entity>();
        this.world = world;
    }

    /**
     * Render the entities in the world.
     */
    public void render() {
        for (Entity e : entityMap.values()) {
            if (e instanceof Player) {
                continue;
            }

            e.render();
        }
    }

    /**
     * Update the entities in the world.
     * 
     * @param delta The delta time
     */
    public void update(double delta) {
        for (Entity e : entityMap.values()) {
            e.update(delta);
        }
    }

    /**
     * Dispose of this manager.
     */
    public void dispose() {
        for (Entity e : entityMap.values()) {
             ///world..addEntiy(e.getLocation().toString(), e.getId());
        }

        for (Entity e : entityMap.values()) {
            e.dispose();
        }
    }

    /**
     * Add an entity to the manager. This adds it to the update and render loops.
     * 
     * @param e The entity to add
     */
    public void addEntity(Entity e) {
        entityMap.put(e.getId(), e);
    }

    /**
     * Remove an entity from the manager. This removes it from the update and render loops.
     * 
     * @param e The entity to remove.
     */
    public void removeEntity(Entity e) {
        entityMap.remove(e.getId());

    }

    /**
     * Remove an entity by ID. {@link removeEntity} does the same thing but with an instance of entity.
     * 
     * @param id The ID of the entity to remove.
     */
    public void removeEntity(int id) {
        entityMap.remove(id);
    }

    /**
     * Get the entity with the given ID. CAUTION: Will return null!
     * 
     * @param id The ID of the entity to fetch.
     * @return The entity or null if its not registered with this manager.
     */
    public Entity getEntity(int id) {
        return entityMap.get(id);
    }

    /**
     * Get the entity at a given location.
     * 
     * @deprecated Not very accurate and returns null very easily.
     * @param l The location to check for an entity.
     * @return The entity at the location or null if none.
     */
    public Entity getEntity(Location l) {
        for (Entity e : entityMap.values()) {
            if (e.getLocation().getWorld() == l.getWorld() && e.getLocation().getX() == l.getX()
                    && e.getLocation().getY() == l.getY()) {
                return e;
            }
        }

        return null;
    }

    /**
     * Get all the entities registered with this manager.
     * 
     * @return A collection of all the entities.
     */
    public Collection<Entity> getEntities() {
        return this.entityMap.values();
    }
}
