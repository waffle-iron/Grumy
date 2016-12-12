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
package org.plexian.grumy.world;

import org.plexian.grumy.Game;
import org.plexian.grumy.math.SimplexNoise;
import org.plexian.grumy.tile.Tile;

/**
 * 
 * @author walt
 *
 */
public class WorldGenerator {
    private static SimplexNoise noise = new SimplexNoise();
    private static float frequency = 20f;

    private static WorldGeneratorBottom bottomGenerator = new WorldGeneratorBottom();
    private static WorldGeneratorStone stoneGenerator = new WorldGeneratorStone(noise, frequency);
    private static WorldGeneratorDirt dirtGenerator = new WorldGeneratorDirt(noise, frequency);
    private static WorldGeneratorAir airGenerator = new WorldGeneratorAir();
    private static WorldGeneratorTree treeGenerator = new WorldGeneratorTree();

    public static void generateChunk(Chunk c) {
        if (!Game.LEVEL_BUILDER) {
            int[][] tiles = c.getTiles();
            /*
             * tiles = stoneGenerator.generateChunk(tiles,
             * c.getPosition().getX(), c.getPosition().getY()); tiles =
             * dirtGenerator.generateChunk(tiles, c.getPosition().getX(),
             * c.getPosition().getY()); tiles =
             * airGenerator.generateChunk(tiles, c.getPosition().getX(),
             * c.getPosition().getY()); tiles =
             * treeGenerator.generateChunk(tiles, c.getPosition().getX(),
             * c.getPosition().getY());
             */
            for (int x = 0; x < tiles.length; x++) {
                for (int y = 0; y < tiles.length; y++) {
                    c.setTile(x, y, Tile.AIR.getId());
                }
            }
        }
        // Layer.generateLayer(c);
    }

    public static void generateWorld(World w) {
        int[][] tiles = bottomGenerator.generateWorld(w);
        /*
         * tiles = stoneGenerator.generateWorld(tiles); tiles =
         * dirtGenerator.generateWorld(tiles); tiles =
         * airGenerator.generateWorld(tiles);
         */

        for (Chunk c : w.getChunks()) {
            for (int x = 0; x < (int) Game.CHUNK_SIZE; x++) {
                for (int y = 0; y < (int) Game.CHUNK_SIZE; y++) {
                    c.setTile(x, y, tiles[x + (int) c.getPosition().getX()][y + (int) c.getPosition().getY()]);
                }
            }

            c.rebuild();
        }

        for (int x = 0; x < Game.WORLD_SIZE + 1; x++) {
            Chunk c = w.getChunk(x * 15d, Game.WORLD_SIZE * 15d);

            for (int x1 = 0; x1 < Game.CHUNK_SIZE; x1++) {
                for (int y = 0; y < Game.CHUNK_SIZE; y++) {
                    c.setTile(x1, y, Tile.AIR.getId());
                }
            }

            c.rebuild();
        }
    }
}
