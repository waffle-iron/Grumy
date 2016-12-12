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
package org.plexian.grumy.tile;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.plexian.grumy.Game;
import org.plexian.grumy.entity.player.Player;
import org.plexian.grumy.opengl.SpriteSheet;

/**
 * The main class for tiles. Holds a static map of all tiles and their ids.
 * @author Walt
 * @since 0.0.1
 */
public abstract class Tile {
	/**
	 * The map where we store all the tiles.
	 */
	public static HashMap<Integer, Tile> tileMap = new HashMap<Integer, Tile>();
	
	/**
	 * These are instances of all the tiles.
	 */
	public static Tile AIR = new TileAir();
	public static Tile STARS_WITH_PLANET = new TileStarsPlanet();
	public static Tile STARS_NO_PLANET = new TileStars();
	public static Tile STEEL = new TileSteel();
	public static Tile SPACE_DOOR_CLOSED = new TileSpaceDoorClosed();
	public static Tile SPACE_DOOR_ONE_THIRD = new TileSpaceDoorOneThird();
	public static Tile SPACE_DOOR_TWO_THIRDS = new TileSpaceDoorTwoThirds();
	public static Tile SPACE_DOOR_OPEN = new TileSpaceDoorOpen();
	public static Tile ITEM_SLOT = new TileItemSlot();
	public static Tile ITEM_KEY = new TileItemKey();
	public static Tile KEY_PICKUP = new TileKeyPickup();
	public static Tile SANTA_PRESENT = new TileSantaPresent();
	public static Tile SAND = new TileSand();
	public static Tile SANDSTONE = new TileSandstone();
	public static Tile CHAIN_LEFT = new TileChainLeft();
	public static Tile CHAIN_RIGHT = new TileChainRight();
	public static Tile DOOR = new TileDoor();
	
	public static Tile PLAYER_LEFT = new TilePlayerLeft();
	public static Tile PLAYER_RIGHT = new TilePlayerRight();
	public static Tile ANTI = new TileAnti();
	
	public abstract int getId();
	public abstract boolean isTransparent();
	public abstract boolean isAnimated();
	public abstract boolean isSolid();

	public abstract float[] getTextureCoordinates();

	static{
		Tile.registerTile(0, AIR);
		Tile.registerTile(1, STARS_WITH_PLANET);
		Tile.registerTile(2, STARS_NO_PLANET);
		Tile.registerTile(3, STEEL);
		Tile.registerTile(4, SPACE_DOOR_CLOSED);
		Tile.registerTile(5, SPACE_DOOR_ONE_THIRD);
		Tile.registerTile(6, SPACE_DOOR_TWO_THIRDS);
		Tile.registerTile(7, SPACE_DOOR_OPEN);
		Tile.registerTile(8, ITEM_SLOT);
		Tile.registerTile(9, ITEM_KEY);
		Tile.registerTile(10, KEY_PICKUP);
		Tile.registerTile(11, SANTA_PRESENT);
		Tile.registerTile(12, SAND);
		Tile.registerTile(13, SANDSTONE);
		Tile.registerTile(14, CHAIN_LEFT);
		Tile.registerTile(15, CHAIN_RIGHT);
		Tile.registerTile(16, DOOR);
		Tile.registerTile(901, PLAYER_LEFT);
		Tile.registerTile(900, PLAYER_RIGHT);
	}
	
	/**
	 * Get an instance of Tile with id id.
	 * @param id The id of the tile.
	 * @return An instance of tile with id id, or an instance of TileAir.
	 */
	public static Tile getTile(int id){
		return tileMap.get(id) != null ? tileMap.get(id) : tileMap.get(0);
	}
	
	public static void registerTile(int id, Tile tile){
		tileMap.put(id, tile);
	}
	
	/**
	 * Draw a tile at (x, y) with size size.
	 * @param x The X-coordinate to draw tile at (in world coordinates).
	 * @param y The Y-coordinate to draw tile at (in world coordinates).
	 * @param size The size (in tile sizes) to draw the tile as.
	 */
	public static void draw(double x, double y, int id, float size){
		float[] textureCoordinates = Tile.getTile(id).getTextureCoordinates();
		textureCoordinates[0] *= SpriteSheet.tileTextures.uniformSize();
		textureCoordinates[1] *= SpriteSheet.tileTextures.uniformSize();
		
		x *= Game.TILE_SIZE;
		y *= Game.TILE_SIZE;
		size *= Game.TILE_SIZE;
		
		if(Tile.getTile(id).isTransparent()){
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.0f);
		}
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(textureCoordinates[0], textureCoordinates[1] + SpriteSheet.tileTextures.uniformSize());
			GL11.glVertex2d(x, y);
		GL11.glTexCoord2f(textureCoordinates[0] + SpriteSheet.tileTextures.uniformSize(), textureCoordinates[1] + SpriteSheet.tileTextures.uniformSize());
			GL11.glVertex2d(x + size, y);
		
		GL11.glTexCoord2f(textureCoordinates[0] + SpriteSheet.tileTextures.uniformSize(), textureCoordinates[1]);
			GL11.glVertex2d(x + size, y + size);
		
		GL11.glTexCoord2f(textureCoordinates[0], textureCoordinates[1]);
		GL11.glVertex2d(x, y + size);
		
		GL11.glEnd();
	}
	
	public static void drawPlayer(Player p, int x, int y){
		float[] textureCoordinates = p.getSprite().getTextureCoordinates();
		textureCoordinates[0] *= SpriteSheet.tileTextures.uniformSize();
		textureCoordinates[1] *= SpriteSheet.tileTextures.uniformSize();
		
		x *= Game.TILE_SIZE;
		y *= Game.TILE_SIZE;
		float size = Game.PLAYER_SIZE;
		
		if(p.getSprite().isTransparent()){
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.0f);
		}
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(textureCoordinates[0], textureCoordinates[1] + SpriteSheet.tileTextures.uniformSize());
			GL11.glVertex2f(x, y);
		GL11.glTexCoord2f(textureCoordinates[0] + SpriteSheet.tileTextures.uniformSize(), textureCoordinates[1] + SpriteSheet.tileTextures.uniformSize());
			GL11.glVertex2f(x + size, y);
		
		GL11.glTexCoord2f(textureCoordinates[0] + SpriteSheet.tileTextures.uniformSize(), textureCoordinates[1]);
			GL11.glVertex2f(x + size, y + size);
		
		GL11.glTexCoord2f(textureCoordinates[0], textureCoordinates[1]);
			GL11.glVertex2f(x, y + size);
		
		GL11.glEnd();
	}
}