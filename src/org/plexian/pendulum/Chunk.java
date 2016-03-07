/**
 * Copyright Plexian Studios, 2014-2016. All Rights Reserved.
 * Released under Plexian License (see plexian.org/license).
 * If unable to obtain license, please email support@plexian.org.
 */
package org.plexian.pendulum;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.plexian.pendulum.Game;

/**
 * Chunk class to manage large ammount of tiles easily, without putting stress on a lookup-matrix.
 * @author Walt
 * @since 0.0.2
 */
public class Chunk {
	/**
	 * This is the unique id for our OpenGL display list.
	 * OpenGL uses ids instead of instances to handle display lists, so this is pretty much our access-token.
	 */
	private int vcId;
	
	/**
	 * This is the chunk type for this chunk. There are 2 types so far: 0 (AIR), and 1 (MIXED).
	 */
	private int type;
	
	/**
	 * This is simply an accessible way to store the original chunk position. Everything is set using offsets from
	 * this point.
	 */
	private Vector2d position;
	
	/**
	 * This is simply an accessible way to store the max-offset that the chunk has. It is exactly at the end of the chunk,
	 * on both X and Y axis.
	 */
	private Vector2d endPosition;
	
	/**
	 * This is an array holding the ids of each tile. Some people store the actual tiles in an array, but memory wise this
	 * is much simpler and more efficient.
	 */
	private int[][] tiles = new int[(int)Game.CHUNK_SIZE][(int)Game.CHUNK_SIZE];
	private int[][] tileAnimationStages = new int[(int)Game.CHUNK_SIZE][(int)Game.CHUNK_SIZE];
	
	/**
	 * This is simply an easy way to generate the chunk's blocks.
	 */
	public static Random random = new Random();
	
	/**
	 * This is the shader program for the chunk. Passed by the world.
	 */
	private ShaderProgram shaderProgram;
	
	/**
	 * The ChunkState this chunk is in.
	 */
	private ChunkState state;
	
	/**
	 * This is just an enum that manages chunk states.
	 * @author walt
	 * @since 0.3
	 */
	public enum ChunkState{
		/**
		 * This is the ChunkState if the chunk is being saved.
		 */
		SAVING(1),
		
		/**
		 * This is the ChunkState if the chunk is being deleted.
		 */
		DELETING(2),
		
		/**
		 * This is the ChunkState if the chunk is being built.
		 */
		BUILDING(3),
		
		/**
		 * This is the ChunkState if the chunk is just running.
		 */
		PASSIVE(4),
		
		/**
		 * This is the ChunkState if the chunk is being loaded from the disk.
		 */
		LOADING(5),
		
		/**
		 * This is the ChunkState if the chunk is angry.
		 */
		YOMAMA(6);
		
		/**
		 * Each state has an id that we can store.
		 */
		private int id;
		
		/**
		 * Creates a new instance of ChunkState with id id.
		 * @param id The id of the ChunkState.
		 */
		ChunkState(int id){
			this.id = id;
		}

		/**
		 * Fetch the id of the ChunkState.
		 * @return The id of the ChunkState.
		 */
		public int getId(){
			return id;
		}
	}
	/**
	 * Create a new chunk at the chunk-coordinates (x, y), with type type.
	 * -Removed for now- @param shaderProgram The Shader Program to use for this chunk, generally passed by the World class.
	 * @param x The X-coordinate to create a chunk at.
	 * @param y The Y-coordinate to create a chunk at.
	 * @param type The chunk type. (@see type).
	 */
	public Chunk(/*ShaderProgram shaderProgram,*/ double x, double y, int type){
		/**
		 * Multiply the X axis location of the chunk by 16, thus giving it world-coordinates.
		 */
		x *= (int)Game.CHUNK_SIZE;
		
		/**
		 * Multiply the Y axis location of the chunk by 16, thus giving it world-coordinates.
		 */
		y *= (int)Game.CHUNK_SIZE;
		
		/**
		 * Set the position of the chunk to be an instance of Vector2f at calculated world coordinates.
		 */
		this.position = new Vector2d(x, y);
		
		/**
		 * We kinda sometimes want to know where the edge of a chunk is, so we calculate this by adding the 
		 * size of the chunk to the world position.
		 */
		this.endPosition = new Vector2d(x + (int)Game.CHUNK_SIZE, y + (int)Game.CHUNK_SIZE);
		
		/**
		 * Set the type of this chunk to the given type.
		 */
		this.type = type;

		/**
		 * We need an anchor for our display list, so we generate the display list.
		 */
		this.vcId = GL11.glGenLists(1);
		
		/**
		 * Set the chunk state to be passive.
		 */
		this.state = ChunkState.PASSIVE;
		
		/**
		 * We need to generate the chunk, so let's tile that tile list.
		 */
		build();
		
		/**
		 * Rebuild the display list.
		 */
		rebuild();
	}
	
	/**
	 * This method rebuilds the chunk's display list as well as generates it.
	 */
	public void rebuild(){
		/**
		 * Set the state of the chunk to be in-building. This locks the chunk from being saved or loaded.
		 */
		this.state = ChunkState.BUILDING;
		
		GL11.glNewList(vcId, GL11.GL_COMPILE);
		
		/**
		 * Loop through all the blocks in the chunk.
		 */
		for(int x = 0; x < Game.CHUNK_SIZE; x++){
			for(int y = 0; y < Game.CHUNK_SIZE; y++){
				/**
				 * If the tile isn't air, run the OpenGL commands to draw a tile with id of tiles.
				 */
				if(tiles[x][y] != 0){
					Tile.draw((int)(x + position.x), (int)(y + position.y), tiles[x][y], 1);
				}
			}
		}
		
		/**
		 * Tell OpenGL thats all the commands it gets.
		 */
		GL11.glEndList();
		
		/**
		 * Unlock the chunk for saving and loading.
		 */
		this.state = ChunkState.PASSIVE;
	}
	
	/**
	 * Build the chunk's tiles array.
	 */
	public void build(){
		/**
		 * Lock the chunk from saving and loading.
		 */
		this.state = ChunkState.BUILDING;
		
		SimplexNoise noise = new SimplexNoise();
		
		/**
		 * Loop through all the tiles in the chunk.
		 */
		if(this.type == 0){
			for(int x = 0; x < Game.CHUNK_SIZE; x++){
				for(int y = 0; y < Game.CHUNK_SIZE; y++){
					this.setTile(x, y, Tile.AIR.getId());
				}
			}
		}
		/**
		 * Unlock chunk for saving/loading.
		 */
		this.state = ChunkState.PASSIVE;
	}
	
	/**
	 * This method simply calls the display list, thereby rendering the chunk.
	 */
	public void render(){
		/**
		 * Calls the OpenGL display list.
		 */
		GL11.glCallList(vcId);
	}
	
	/**
	 * This method updates any part of the chunk needed.
	 */
	public void update(boolean tick){
		if(tick){
			for(int x = 0; x < Game.CHUNK_SIZE; x++){
				for(int y = 0; y < Game.CHUNK_SIZE; y++){
					if(Tile.getTile(tiles[x][y]).isAnimated()){
						tiles[x][y] = ((AnimatedTile)Tile.getTile(tiles[x][y])).animate(tileAnimationStages[x][y]);
						tileAnimationStages[x][y]++;
					
						if(tileAnimationStages[x][y] > ((AnimatedTile)Tile.getTile(tiles[x][y])).getMaxStage()){
							tileAnimationStages[x][y] = 0;
						}
						
						this.rebuild();
					}
				}
			}
		}
	}
	
	/**
	 * Destroys the chunk.
	 */
	public void dispose(){
		/**
		 * Locks the chunk from saving/loading.
		 */
		this.state = ChunkState.DELETING;
		
		/**
		 * Deletes the display list for the chunk.
		 */
		GL11.glDeleteLists(vcId, 1);
		
		/**
		 * Unlock chunk for saving/loading.
		 */
		this.state = ChunkState.PASSIVE;
	}
	
	/**
	 * Fetch the id of the tile at (x, y).
	 * @param x The X-coordinate to get the tile at.
	 * @param y The Y-coordinate to get the tile at.
	 * @return The tile at (x, y) or 0 if not inside this chunk.
	 */
	public int getTile(int x, int y){
		/**
		 * Basically this is a failsafe to ensure that we don't get an IndexArrayOutOfBounds exception.
		 * If the x or y given is greater than 15, or x or y < 0, then return 0.
		 */
		if(x > 15 || y > 15 || x < 0 || y < 0){
			return 0;
		}
		
		/**
		 * Return the tile id at the (x, y) location.
		 */
		return tiles[x][y];
	}
	
	/**
	 * Set a tile at (x, y) with type type.
	 * @param x The X-coordinate to place a tile at.
	 * @param y The Y-coordinate to place a tile at.
	 * @param type The id of the tile to place at (x, y).
	 */
	public void setTile(int x, int y, int type){
		/**
		 * Basically this is a failsafe to ensure that we don't get an IndexArrayOutOfBounds exception.
		 * If the x or y given is greater than 15, or x or y < 0, then return 0.
		 */
		if(x > 15 || y > 15 || x < 0 || y < 0){
			return;
		}
		
		/**
		 * Set the tile id at (x, y) to type.
		 */
		tiles[x][y] = type;
		
		if(Tile.getTile(type) instanceof AnimatedTile){
			tileAnimationStages[x][y] = 0;
		}
	}
	
	/**
	 * Get the position of this chunk, in world coordinates.
	 * @return The position of this chunk in world coordinates.
	 */
	public Vector2d getPosition(){
		return this.position;
	}
	
	/**
	 * Get the current chunk state.
	 * @return The current chunk state.
	 */
	public ChunkState getState(){
		return this.state;
	}
	
	/**
	 * Set the chunk state, locking/unlocking the chunk for loading/saving.
	 * @param state The state to set.
	 */
	public void setState(ChunkState state){
		this.state = state;
	}
	
	public int[][] getTiles(){
		return tiles;
	}

	public AABB getTileAsAABB(int x, int y){
		return new AABB(new Vector2d(x + this.position.x, y + this.position.y), new Vector2d(Game.TILE_SIZE, Game.TILE_SIZE));
	}
}
