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

import org.lwjgl.input.Keyboard;
import org.plexian.grumy.Game;
import org.plexian.grumy.math.Vector2d;
import org.plexian.grumy.opengl.Shader;
import org.plexian.grumy.tile.Tile;
import org.plexian.grumy.world.Location;
import org.plexian.grumy.world.World;

/**
 * NOTE: Not document right now. Not sure if this is going to be used.
 */
public class Anti extends LivingEntity {

    public Anti(Location location) {
        this(new Shader("anti.vert", "anti.frag"), location.getWorld(), location.getX(), location.getY(), 25, 25);
    }

    public Anti(Shader shader, World w, double x, double y, double sizeX, double sizeY) {
        super(shader, 100, 100, new Location(w, x, y), new Vector2d(sizeX, sizeY));
    }

    @Override
    public void render() {
        if (this.alive()) {
            program.use();
            Tile.draw(location.getX(), location.getY(), Tile.ANTI.getId(), 1f);
            program.release();
        }
    }

    boolean left = true, right = false;
    int move = 1;

    @Override
    public void update(double delta) {
        if (!Keyboard.isKeyDown(Keyboard.KEY_Z)) {
            if (left) {
                this.location.add(-move, 0);
            } else {
                this.location.add(move, 0);
            }

            if (this.location.getX() < 0) {
                this.left = false;
            } else if (this.location.getX() > 15) {
                this.left = true;
            }
        }

        if (Math.abs(Game.world.entityManager.getEntity(0).getLocation().getX() - this.location.getX()) < 1
                && Math.abs(Game.world.entityManager.getEntity(0).getLocation().getY() - this.location.getY()) < 1) {
            this.life = 0;
        }

        movePossible(delta);
        movePossible(delta);
    }

    public void movePossible(double delta) {
        if (this.velocity.x % 2 == 0 || this.velocity.y % 2 == 0) {
            this.oldLocation.setX(this.location.getX());
            this.oldLocation.setY(this.location.getY());
            this.location.setX(this.location.getX() + (this.velocity.getX() * delta));
            this.location.setY(this.location.getY() + (this.velocity.getY() * delta));
            this.aabb.move(this.location.getX(), this.location.getY());
            this.velocity.multiply(0, 0);

            if (this.location.getX() < 0 || this.location.getY() < 0
                    || this.location.getX() >= (Game.CHUNK_SIZE * Game.WORLD_SIZE)
                    || this.location.getY() >= (Game.CHUNK_SIZE * Game.WORLD_SIZE)) {
                this.location.setX(this.oldLocation.getX());
                this.location.setY(this.oldLocation.getY());
                this.aabb.move(this.oldLocation.getX(), this.oldLocation.getY());
            }

            if (Game.world.checkForTileCollision(this.aabb)) {
                this.location.setX(this.oldLocation.getX());
                this.location.setY(this.oldLocation.getY());
                this.aabb.move(this.oldLocation.getX(), this.oldLocation.getY());
            }
        }
    }
}
