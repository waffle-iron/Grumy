/**
 * Copyright Plexian Studios, 2014-2016. All Rights Reserved.
 * Released under Plexian License (see plexian.org/license).
 * If unable to obtain license, please email support@plexian.org.
 */
package org.plexian.pendulum;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * Provides all the methods needed for the player.
 * @author walt
 */
public class Player extends LivingEntity{
	public static float STANDARD_VELOCITY = 1.0f;
	private boolean jumpPressed, jumpWasPressed, jumpWasPressedTwo;
	private int jumps = 2;
	private double delta;
	private Tile sprite;
	
	/**
	 * Creates a player at location.
	 * @param location The location to create player at.
	 */
	public Player(Location location) {
		super(new Shader("player.vert", "player.frag"), location, new Vector2d(Game.PLAYER_SIZE, Game.PLAYER_SIZE));
	
		sprite = Tile.PLAYER_RIGHT;
		if(Game.LEVEL_BUILDER){
			STANDARD_VELOCITY = 0.5f;
		}else{
			STANDARD_VELOCITY = .5f;
		}
	}

	/**
	 * Draw's the player.
	 */
	@Override
	public void render(){
		this.program.use();
		this.inventory.render();
		this.renderLifeBar();
		GL11.glTranslatef(Display.getWidth() / 2 - Game.PLAYER_SIZE, Display.getHeight() / 2 - Game.PLAYER_SIZE, 1);
		Tile.drawPlayer(this, 0, 0);
		this.program.release();
	}
	
	/**
	 * Updates the player, processes any keyboard input, and checks for collision.
	 */
	@Override
	public void update(double delta){
		this.STANDARD_VELOCITY = .5f;
		
		this.delta = delta;
		this.jumpWasPressedTwo = this.jumpWasPressed;
		this.jumpWasPressed = jumpPressed;
		this.jumpPressed = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
		
		checkForPickup();
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A) && !jumpWasPressedTwo){
			this.sprite = Tile.PLAYER_LEFT;
			this.addVelocity(-STANDARD_VELOCITY, this.velocity.getY());
			movePossible(this.delta);
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_D) && !jumpWasPressedTwo){
			this.sprite = Tile.PLAYER_RIGHT;
			this.addVelocity(STANDARD_VELOCITY, this.velocity.getY());
			movePossible(this.delta);
		}

		if(Game.world.getTile((int)location.getX(), (int)location.getY() - 1) != Tile.AIR.getId()){
			jumps = 2;
		}
		
		if(Game.LEVEL_BUILDER && jumpPressed){
			this.addVelocity(this.velocity.getX(), STANDARD_VELOCITY);
			movePossible(this.delta);
		}
		
		if(!Game.LEVEL_BUILDER && !jumpPressed && !jumpWasPressedTwo){
			this.addVelocity(this.velocity.getX(), -STANDARD_VELOCITY);
			movePossible(this.delta);
		}else if(!Game.LEVEL_BUILDER && jumpPressed && jumps-- > 0){
			this.addVelocity(this.velocity.getX(), STANDARD_VELOCITY);
			movePossible(this.delta);
		}
		
		if(Game.world.getTile((int)location.getX(), (int)location.getY()) > Tile.AIR.getId()){
			jumps = 2;
		}
	
		if(Game.LEVEL_BUILDER){
			if(Keyboard.isKeyDown(Keyboard.KEY_P)){
				Game.world.setTile(location.getX(), location.getY(), 1);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_2)){
				Game.world.setTile(location.getX(), location.getY(), 2);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_3)){
				Game.world.setTile(location.getX(), location.getY(), 3);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_4)){
				Game.world.setTile(location.getX(), location.getY(), 4);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_5)){
				Game.world.setTile(location.getX(), location.getY(), 5);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_6)){
				Game.world.setTile(location.getX(), location.getY(), 6);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_7)){
				Game.world.setTile(location.getX(), location.getY(), 7);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_8)){
				Game.world.setTile(location.getX(), location.getY(), 8);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_9)){
				Game.world.setTile(location.getX(), location.getY(), 9);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_0)){
				Game.world.setTile(location.getX(), location.getY(), 0);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_1) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))){
				Game.world.setTile(location.getX(), location.getY(), 10);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_2) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))){
				Game.world.setTile(location.getX(), location.getY(), 11);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_3) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))){
				Game.world.setTile(location.getX(), location.getY(), 13);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_4) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))){
				Game.world.setTile(location.getX(), location.getY(), 14);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_5) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))){
				Game.world.setTile(location.getX(), location.getY(), 15);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_6) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))){
				Game.world.setTile(location.getX(), location.getY(), 16);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_7) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))){
				Game.world.setTile(location.getX(), location.getY(), 17);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_8) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))){
				Game.world.setTile(location.getX(), location.getY(), 18);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_9) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))){
				Game.world.setTile(location.getX(), location.getY(), 19);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_0) && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))){
				Game.world.setTile(location.getX(), location.getY(), 20);
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_P)){
				Game.world.setTile(location.getX(), location.getY(), 11);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_2)){
				Game.world.setTile(location.getX(), location.getY(), 12);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_3)){
				Game.world.setTile(location.getX(), location.getY(), 13);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_4)){
				Game.world.setTile(location.getX(), location.getY(), 14);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_5)){
				Game.world.setTile(location.getX(), location.getY(), 15);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_6)){
				Game.world.setTile(location.getX(), location.getY(), 16);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_7)){
				Game.world.setTile(location.getX(), location.getY(), 17);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_8)){
				Game.world.setTile(location.getX(), location.getY(), 18);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_9)){
				Game.world.setTile(location.getX(), location.getY(), 19);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_0)){
				Game.world.setTile(location.getX(), location.getY(), 0);
			}
			
			else if(Keyboard.isKeyDown(Keyboard.KEY_M)){
				Game.world.setTile((int)location.getX(), (int)location.getY() - 1, Tile.AIR.getId());
				Game.world.setTile((int)location.getX(), (int)location.getY() + 1, Tile.AIR.getId());
				Game.world.setTile((int)location.getX() - 1, (int)location.getY(), Tile.AIR.getId());
				Game.world.setTile((int)location.getX() + 1, (int)location.getY(), Tile.AIR.getId());
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				this.addVelocity(0, -STANDARD_VELOCITY);
				this.movePossible(delta);
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			if(Game.world.isDigableTile(Game.world.getTile(this.location.getX(), this.location.getY() - 1))){
				Game.world.setTile(this.location.getX(), this.location.getY() - 1, Tile.AIR.getId());
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_L)){
			Game.LEVEL_BUILDER = !Game.LEVEL_BUILDER;
			Player.STANDARD_VELOCITY = (Player.STANDARD_VELOCITY == 1f ? .5f : 1f);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_I)){
			Game.LOG.info("Player information: ");
			Game.LOG.info("  location: " + this.location.toString());
			Game.LOG.info("  life: " + this.life);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_K)){
			this.life--;
		}
		
		this.velocity.multiply(0, 0);
	}
	
	/**
	 * Check if the player can move to where they are now.
	 * @return True if they can, false if not.
	 */
	public void movePossible(double delta){
		if(this.velocity.x % 2 == 0 || this.velocity.y % 2 == 0){
			this.oldLocation.setX(this.location.getX());
			this.oldLocation.setY(this.location.getY());
			this.location.setX(this.location.getX() + (this.velocity.getX() * delta));
			this.location.setY(this.location.getY() + (this.velocity.getY() * delta));
			this.aabb.move(this.location.getX(), this.location.getY());
			this.velocity.multiply(0, 0);
			
			/**
			 * If we are in level-editor mode.
			 */
			if(Game.LEVEL_BUILDER){
				if(this.location.getX() < 0 || this.location.getY() < 0 || this.location.getX() >= (Game.CHUNK_SIZE * Game.WORLD_SIZE) || this.location.getY() >= (Game.CHUNK_SIZE * Game.WORLD_SIZE)){
					this.location = this.oldLocation;
					this.aabb.move(this.location.getX(), this.location.getY());
					return;
				}
				
				/**
				 * If the O-key is down, then allow the player to phase through tiles.
				 */
				if(Keyboard.isKeyDown(Keyboard.KEY_O)){
					return;
				}
				
				return;
			}
			
			if(this.location.getX() < 0 || this.location.getY() < 0 || this.location.getX() >= (Game.CHUNK_SIZE * Game.WORLD_SIZE) || this.location.getY() >= (Game.CHUNK_SIZE * Game.WORLD_SIZE)){
				this.location.setX(this.oldLocation.getX());
				this.location.setY(this.oldLocation.getY());
				this.aabb.move(this.oldLocation.getX(), this.oldLocation.getY());
			}
			
			if(Game.world.checkForTileCollision(this.aabb)){
				this.location.setX(this.oldLocation.getX());
				this.location.setY(this.oldLocation.getY());
				this.aabb.move(this.oldLocation.getX(), this.oldLocation.getY());
			}
		}
	}
	
	/**
	 * Add a value to the player's velocity.
	 * @param x The X-velocity to set.
	 * @param y The Y-velocity to set.
	 */
	public void addVelocity(double x, double y){
		this.velocity.add(x / delta, y / delta);
	}
	
	@Override
	public boolean alive(){
		if(life <= 0){
			return false;
		}
		
		return true;
	}
	
	public Tile getSprite(){
		return this.sprite;
	}
	
	public void checkForPickup(){
		if(Game.world.getTile(this.location.getX(), this.location.getY()) == Tile.KEY_PICKUP.getId()){
			this.inventory.addItem(Item.KEY);
			Game.world.keyManager.checkForPickup(this.location);
		}
	}
	
	public void renderLifeBar(){
		float[] textureCoordinates;
		
		int ammount = (int)this.life / 20;
		int beginningX = Display.getWidth() - (int)(Game.TILE_SIZE / 1.5f) -  ((int)ammount * (int)(Game.TILE_SIZE / 1.5f));
	
		for(int i = 0; i < ammount; i++){
			int x = beginningX + (i * (int)(Game.TILE_SIZE / 1.5f));
			
			textureCoordinates = Item.HEART.getTextureCoordinates();
			textureCoordinates[0] *= SpriteSheet.tileTextures.uniformSize();
			textureCoordinates[1] *= SpriteSheet.tileTextures.uniformSize();
			
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(textureCoordinates[0], textureCoordinates[1] + SpriteSheet.tileTextures.uniformSize());
				GL11.glVertex2f(x, Display.getHeight() - Game.TILE_SIZE);
			GL11.glTexCoord2f(textureCoordinates[0] + SpriteSheet.tileTextures.uniformSize(), textureCoordinates[1] + SpriteSheet.tileTextures.uniformSize());
				GL11.glVertex2f(x + Game.TILE_SIZE, Display.getHeight() - Game.TILE_SIZE);
			GL11.glTexCoord2f(textureCoordinates[0] + SpriteSheet.tileTextures.uniformSize(), textureCoordinates[1]);
				GL11.glVertex2f(x + Game.TILE_SIZE, (Display.getHeight() - Game.TILE_SIZE) + Game.TILE_SIZE);
			GL11.glTexCoord2f(textureCoordinates[0], textureCoordinates[1]);
				GL11.glVertex2f(x, (Display.getHeight() - Game.TILE_SIZE) + Game.TILE_SIZE);
			GL11.glEnd();
		}
	}
}
