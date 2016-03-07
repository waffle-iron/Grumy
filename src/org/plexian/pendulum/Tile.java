/**
 * Copyright Plexian Studios, 2014-2016. All Rights Reserved.
 * Released under Plexian License (see plexian.org/license).
 * If unable to obtain license, please email support@plexian.org.
 */
package org.plexian.pendulum;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;

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
	
	/**
	 * TODO: Add these tiles textures.
	 */
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
	
	public static Tile PLAYER_LEFT = new TilePlayerLeft();
	public static Tile PLAYER_RIGHT = new TilePlayerRight();
	public static Tile ANTI = new TileAnti();
	
	public abstract int getId();
	public abstract boolean isTransparent();
	public abstract boolean isAnimated();
	public abstract boolean isSolid();

	public abstract float[] getTextureCoordinates();

	static{
		tileMap.put(0, AIR);
		tileMap.put(1, STARS_WITH_PLANET);
		tileMap.put(2, STARS_NO_PLANET);
		tileMap.put(3, STEEL);
		tileMap.put(4, SPACE_DOOR_CLOSED);
		tileMap.put(5, SPACE_DOOR_ONE_THIRD);
		tileMap.put(6, SPACE_DOOR_TWO_THIRDS);
		tileMap.put(7, SPACE_DOOR_OPEN);
		tileMap.put(8, ITEM_SLOT);
		tileMap.put(9, ITEM_KEY);
		tileMap.put(10, KEY_PICKUP);
		tileMap.put(11, SANTA_PRESENT);
		tileMap.put(12, SAND);
		tileMap.put(13, SANDSTONE);
		tileMap.put(14, CHAIN_LEFT);
		tileMap.put(15, CHAIN_RIGHT);
		
		tileMap.put(901, PLAYER_LEFT);
		tileMap.put(900, PLAYER_RIGHT);
	}
	
	/**
	 * Get an instance of Tile with id id.
	 * @param id The id of the tile.
	 * @return An instance of tile with id id, or an instance of TileAir.
	 */
	public static Tile getTile(int id){
		return tileMap.get(id) != null ? tileMap.get(id) : tileMap.get(0);
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