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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.plexian.grumy.Game;
import org.plexian.grumy.configuration.FileManager;
import org.plexian.grumy.entity.AABB;
import org.plexian.grumy.entity.Entity;
import org.plexian.grumy.entity.EntityManager;
import org.plexian.grumy.entity.player.Player;
import org.plexian.grumy.item.KeyManager;
import org.plexian.grumy.math.Vector2d;
import org.plexian.grumy.opengl.RGBA;
import org.plexian.grumy.opengl.Shader;
import org.plexian.grumy.opengl.ShaderProgram;
import org.plexian.grumy.tile.Tile;
import org.plexian.grumy.world.particle.ParticleFactory;


public class World {
	public List entityList = new ArrayList();
	public List players = new ArrayList();
	public Random random = new Random();
	public int tick = 0;
	
	public static String[] WORLD_NAMES = new String[]{
		"Jig",
		"Blag",
		"Yoda",
		"Dig",
		"Slab",
		"Slouch"
	};
	
	public ShaderProgram shader;
	public FileManager fileManager;
	public EntityManager entityManager;
	public DialogManager dialogManager;
	public KeyManager keyManager;
	
	private String name;
	private ConcurrentHashMap<String, Chunk> chunkMap, chunkRenderMap;
	private boolean load = true;
	private ParticleFactory factory;
	private Player player;
	//private Anti enemy;

	/*
	 * Creates a new instance of World with name name. If world exists, it is loaded
	 * @param name The name of the world.
	 */
	public World(String name, boolean load){
		try{
			this.name = name;
			this.factory = new ParticleFactory(new Vector2d(0, 0));
			Shader temp = new Shader("shaders/world.vert", "shaders/world.frag");
			
			shader = new ShaderProgram(temp.getvShader(), temp.getfShader());
			Game.LOG.info("Generating world...");
			
			this.chunkMap = new ConcurrentHashMap<String, Chunk>();
			this.chunkRenderMap = new ConcurrentHashMap<String, Chunk>();
			
			this.fileManager = new FileManager(this);
			this.entityManager = new EntityManager(this);
			this.dialogManager = new DialogManager(this);
			this.keyManager = new KeyManager(this);
			
			this.player = new Player(new Location(this, 21, 107));
			//this.enemy = new Anti(new Location(this, 10, 10));
			
			this.entityManager.addEntity(player);
		//	this.entityManager.addEntity(enemy);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This rebuilds the world entirely. It resets the world's name to the original, updates the file manager's world directory,
	 * game configuration world name, and regenerates/loads the chunks.
	 */
	public void rebuild(){
		updateName();
		
		Game.LOG.info("Adding dialog...");
		
		if(this.fileManager.getConfiguration().getRootSection().getConfigurationSection("world").getList("dialog") != null){
			for(Object m : this.fileManager.getConfiguration().getRootSection().getConfigurationSection("world").getList("dialog")){
				if(m instanceof HashMap){
					HashMap m1 = (HashMap)m;
					
					String location = "", message = "", color = "";
					
					location = (String)m1.get("location");
					Game.LOG.info(location);
					message = (String)m1.get("message");
					Game.LOG.info(message);
					color = (String)m1.get("color");
					
					dialogManager.addDialog(Location.parseLocation(location), message, RGBA.parse(color));
				}
			}
		}
		
		Game.LOG.info("Adding keys...");
		
		if(this.fileManager.getConfiguration().getRootSection().getConfigurationSection("world").getList("dialog") != null){
			for(Object m : this.fileManager.getConfiguration().getRootSection().getConfigurationSection("world").getList("keys")){
				if(m instanceof HashMap){
					HashMap m1 = (HashMap)m;
					
					String location = (String)m1.get("location");
					int tileReplaceId = (int)m1.get("replace");
					
					keyManager.addKey(Location.parseLocation(location), tileReplaceId);
				}
			}
		}
		
		Game.LOG.info("Generating Chunks... May take a while.");
		
		for(int x = 0; x < Game.WORLD_SIZE; x++){
			for(int y = 0; y < Game.WORLD_SIZE; y++){
				Chunk c = fileManager.loadChunk(x, y);
				
				if(c != null && this.load){
					chunkMap.put(new Vector2d(x, y).toString(), c);
				}else{
					if(Game.LEVEL_BUILDER){
						chunkMap.put(new Vector2d(x, y).toString(), new Chunk(/*new ShaderProgram(defaultShader.getvShader(), defaultShader.getfShader()),*/ x, y, 0));
					}else{
						/**
						 * If we on the top chunk
						 */
						chunkMap.put(new Vector2d(x, y).toString(), new Chunk(x, y, 2));
					}
				}
			}
		}
		
		if(!Game.LEVEL_BUILDER && !Game.LOAD_WORLD){
			WorldGenerator.generateWorld(this);
		}
	}
	
	/**
	 * This simply renders the chunks.
	 */
	public void render(){
		entityManager.getEntity(0).render();
		
		/**
		 * This moves to the players actual location to draw the player.
		 */
		GL11.glTranslated(-entityManager.getEntity(0).getLocation().getX()  * Game.PLAYER_SIZE, -entityManager.getEntity(0).getLocation().getY() * Game.PLAYER_SIZE, -1);
		
		/**
		 * This draws the player.
		 */
		entityManager.render();
		renderTopObjects();
		
		shader.use();
		for(Chunk c : chunkRenderMap.values()){
			if(Math.sqrt(((c.getPosition().getX() - player.getLocation().getX())*(c.getPosition().getX() - player.getLocation().getX())) + ((c.getPosition().getY() - player.getLocation().getY()) * (c.getPosition().getY() - player.getLocation().getY()))) < Game.CHUNK_SIZE*3){
				c.render();
			}
		}
		shader.release();
		
		for(Chunk c : chunkRenderMap.values()){
			if(Math.sqrt(((c.getPosition().getX() - player.getLocation().getX())*(c.getPosition().getX() - player.getLocation().getX())) + ((c.getPosition().getY() - player.getLocation().getY()) * (c.getPosition().getY() - player.getLocation().getY()))) < Game.CHUNK_SIZE*3){
				c.update(tick % 2 == 0);
			}
		}
	}

	public void renderTopObjects(){
		factory.render();
		dialogManager.render();
	}
	
	/**
	 * This simply updates the chunks.
	 */
	public void update(double delta){
		this.tick++;
		
		entityManager.update(delta);
		
		chunkRenderMap.clear();
		chunkRenderMap.put(player.getLocation().toVector2d().toString(), getChunk(player.getLocation().getX(), player.getLocation().getY()));
	
		/**
		 * One chunk out in each direction.
		 */
		chunkRenderMap.put(player.getLocation().toVector2d().add(Game.CHUNK_SIZE, 0).toString(), getChunk(player.getLocation().getX() + Game.CHUNK_SIZE, player.getLocation().getY()));
		chunkRenderMap.put(player.getLocation().toVector2d().add(-Game.CHUNK_SIZE, 0).toString(), getChunk(player.getLocation().getX() - Game.CHUNK_SIZE, player.getLocation().getY()));
		chunkRenderMap.put(player.getLocation().toVector2d().add(0, Game.CHUNK_SIZE).toString(), getChunk(player.getLocation().getX(), player.getLocation().getY() + Game.CHUNK_SIZE));
		chunkRenderMap.put(player.getLocation().toVector2d().add(0, -Game.CHUNK_SIZE).toString(), getChunk(player.getLocation().getX(), player.getLocation().getY() - Game.CHUNK_SIZE));
		
		chunkRenderMap.put(player.getLocation().toVector2d().add(Game.CHUNK_SIZE, Game.CHUNK_SIZE).toString(), getChunk(player.getLocation().getX() + Game.CHUNK_SIZE, player.getLocation().getY() + Game.CHUNK_SIZE));
		chunkRenderMap.put(player.getLocation().toVector2d().add(Game.CHUNK_SIZE, -Game.CHUNK_SIZE).toString(), getChunk(player.getLocation().getX() + Game.CHUNK_SIZE, player.getLocation().getY() - Game.CHUNK_SIZE));
		chunkRenderMap.put(player.getLocation().toVector2d().add(-Game.CHUNK_SIZE, Game.CHUNK_SIZE).toString(), getChunk(player.getLocation().getX() - Game.CHUNK_SIZE, player.getLocation().getY() + Game.CHUNK_SIZE));
		chunkRenderMap.put(player.getLocation().toVector2d().add(-Game.CHUNK_SIZE, -Game.CHUNK_SIZE).toString(),	getChunk(player.getLocation().getX() - Game.CHUNK_SIZE, player.getLocation().getY() - Game.CHUNK_SIZE));
		
		if(Keyboard.isKeyDown(Keyboard.KEY_P)){
			factory.addParticle();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_T)){
			for(Chunk c : this.chunkMap.values()){
				this.fileManager.saveChunk(c);
			}
		}
	
		keyManager.update();
		
		factory.update();
	}
	
	/**
	 * This saves the world and disposes of the chunks.
	 */
	public void dispose(){
		try{
			Game.LOG.info("Saving world \"" + name + "\"...");
			//entityManager.dispose();
			dialogManager.dispose();
			
			for(Chunk c : chunkMap.values()){
				fileManager.saveChunk(c);
				c.dispose();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the chunk-coordinates from a world position.
	 * @param x The world X-position.
	 * @param y The world Y-position.
	 * @return The chunk-coordinates.
	 */
	public Vector2d worldToChunkCoordinates(double x, double y){
		int x1 = (int)(x / Game.CHUNK_SIZE);
		int y1 = (int)(y / Game.CHUNK_SIZE);
		
		return new Vector2d(x1, y1);
	}
	
	/**
	 * Get the chunk at position (x, y) (x, y, are world coordinates).
	 * @param x The world X-coordinate to get chunk at.
	 * @param y The world Y-coordinate to get chunk at.
	 * @return The chunk at (x, y).
	 */
	public Chunk getChunk(double x, double y){
		Chunk c = chunkMap.get(worldToChunkCoordinates(x, y).toString());

		if(c == null){
			if(x >= 0 && y >= 0){
				chunkMap.put(worldToChunkCoordinates(x, y).toString(), new Chunk(worldToChunkCoordinates(x, y).getX(), worldToChunkCoordinates(x, y).getY(), 1));
		
				c = chunkMap.get(worldToChunkCoordinates(x, y).toString());
				
				WorldGenerator.generateChunk(c);
				Game.WORLD_SIZE++;
				c.rebuild();
			}else{
				chunkMap.put(worldToChunkCoordinates(x, y).toString(), new Chunk(worldToChunkCoordinates(x, y).getX(), worldToChunkCoordinates(x, y).getY(), 2));
				
				c = chunkMap.get(worldToChunkCoordinates(x, y).toString());
				
				Game.WORLD_SIZE++;
			}
		}
	
		return c;
	}
	
	/**
	 * Get the id of tile at (x, y).
	 * @param x The world X-coordinate to get tile at.
	 * @param y The world Y-coordinate to get tile at.
	 * @return The tile id at (x, y).
	 */
	public int getTile(double x, double y){
		Chunk c = getChunk(x, y);
		
		return c.getTile((int)x % (int)Game.CHUNK_SIZE, (int)y % (int)Game.CHUNK_SIZE);
	}
	
	public boolean checkForTileCollision(AABB aabb){
		return (AABB.collides(aabb, this.getTileAsAABB((int)aabb.pos.x, (int)aabb.pos.y)) && Tile.getTile(this.getTile((int)aabb.pos.x, (int)aabb.pos.y)).isSolid());
	}
	
	/**
	 * Set the id of tile at (x, y) with id of type.
	 * @param x The X-coordinate of tile.
	 * @param y The Y-coordinate of tile.
	 * @param type The id of the tile to set.
	 */
	public void setTile(double x, double y, int type){
		x = (double)Math.round(x);
		y = (double)Math.round(y);
		
		getChunk(x, y).setTile((int)(x % (int)Game.CHUNK_SIZE), (int)(y % (int)Game.CHUNK_SIZE), type);
		getChunk(x, y).rebuild();
	}
	
	public AABB getTileAsAABB(int x, int y){
		Chunk c = getChunk(x, y);
		
		return c.getTileAsAABB(x % (int)Game.CHUNK_SIZE, y % (int)Game.CHUNK_SIZE);
	}
	
	public Entity getEntity(double x, double y){
		return entityManager.getEntity(new Location(this, x, y));
	}
	
	/** 
	 * Get the name of the world.
	 * @return The name of the world.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Set the name of the world.
	 * @param name The new world name.
	 */
	public void setName(String name){
		this.name = name;
		
		updateName();
	}
	
	private void updateName(){
		Game.LOG.info("Resetting FileManager listing...");
		fileManager.updateWorldName(this.name);
		Game.LOG.info("Rewriting configuration...");
		Game.CONFIG.getRootSection().put("world.name", name);
		Game.CONFIG.save();
	}
	
	public Collection<Chunk> getChunks(){
		return this.chunkMap.values();
	}
	
	public boolean isDigableTile(int id){
		if(id == Tile.SAND.getId()){
			return true;
		}
		
		return false;
	}
}
