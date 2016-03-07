/**
 * Copyright Plexian Studios, 2014-2016. All Rights Reserved.
 * Released under Plexian License (see plexian.org/license).
 * If unable to obtain license, please email support@plexian.org.
 */
package org.plexian.pendulum;

import java.util.logging.Logger;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * This is the main class that runs the game loop and everything for Pendulum.
 * @author Walt
 * @since 0.1
 */
public class Game {
	/**
	 * The size of the player's character in OpenGL-pixels.
	 */
	public static float PLAYER_SIZE = 65;
	
	/**
	 * The size of a tile in OpenGL-pixels.
	 */
	public static float TILE_SIZE = 65;
	
	/**
	 * The size of a chunk in tiles.
	 */
	public static final float CHUNK_SIZE = 16;
	
	/**
	 * The size of a world in chunks.
	 */
	public static float WORLD_SIZE = 15;
	
	public static final float WORLD_TOP = 14;
	
	/**
	 * The current world.
	 */
	public static World world;
	
	/**
	 * Our very own logger.
	 */
	public static Logger LOG = Logger.getLogger("PendulumGame");
	
	/**
	 * This regulates if we are in level-builder mode.
	 */
	public static boolean LEVEL_BUILDER = false;
	
	public static boolean LOAD_WORLD = false;
	
	/**
	 * This is simply the configuration object for the game.
	 */
	public static YAMLConfiguration CONFIG;
	
	/**
	 * This is just a way to store the world name until we can actually read/calculate it.
	 */
	public static String WORLD_NAME = "level1";
	
	/**
	 * This is the system time at the last frame.
	 */
	private static long lastFrame;
	
	/**
	 * This method is called by the Java Virtual Machine (hereafter JVM).
	 * @param args Command line arguments.
	 */

	/**
	 * This is called by the main method.
	 */
	public void start(){
		LOG.info("Creating game window...");
		
		/**
		 * We use a try loop to be sure that any exceptions are caught.
		 */
		try {
			/**
			 * This sets the display mode to be 800 pixels wide, by 600 pixels height.
			 */
			DisplayMode displayMode = null;
		    DisplayMode[] modes = Display.getAvailableDisplayModes();

		    for (int i = 0; i < modes.length; i++){
		    	if (modes[i].getWidth() == 800 && modes[i].getHeight() == 600 && modes[i].isFullscreenCapable()){
		    		displayMode = modes[i];
		        }
		    }
		    
			/**
			 * This sets the title of the window to be called game.
			 */
			Display.setTitle("Game");
			
			/**
			 * Sets the display mode to fullscreen.
			 */
			Display.setFullscreen(false);
			
			/**
			 * This creates the window.
			 */
			Display.create();
		}catch (Exception e) {
			/**
			 * Basically just print the name and error trace out to the console.
			 */
			e.printStackTrace();
		}
		
		LOG.info("Setting up OpenGL wrapper...");
		
		/**
		 * Heres what we do, line for line:
		 * 
		 * 1. Set the matrix mode to projection.
		 * 2. Reset the graphics card's matrix mode, loading ours.
		 * 3. Set the display up as 2D, with (0, 0) at the bottom left corner.
		 * 4. Set the matrix mode to modelview (what we use for everything else.
		 * 5. Set the background color to a sky blue.
		 * 6. Enable depth test, allowing us to draw the player, depth wise, ontop of the world.
		 * 7. Enable texturing.
		 * 8. Enable Alpha mapping (basically, transparency).
		 * 9. Enable blending to allow for blending of colors if they are somewhat transparent.
		 * 10. Set the blend function to remove anything that is transparent from the textures, thus allowing us to have transparent objects.
		 * 11. Set textures to look pixelated.
		 * 12. Tell OpenGL which textures to use (ours).
		 */
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glClearColor(135f / 225f, 206f / 225f, 250 / 225f, 0f);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_ALPHA);
		//GL11.glEnable(GL11.GL_BLEND);
	    GL11.glAlphaFunc(GL11.GL_GREATER, 0.0f);
		//GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		SpriteSheet.tileTextures.bind();
		
		LOG.info("Generating new world...");
		
		/**
		 * Creates a new instance of World.
		 */
		world = new World(Game.WORLD_NAME, Game.LOAD_WORLD);
			
		/**
		 * Set the name of the world, thus telling it to load/generate a world with that design.
		 */
		world.rebuild();
		
		/**
		 * What we do is enter a loop that runs, updating and rendering the game, until someone presses the escape key or when they click an exit button.
		 */
		LOG.info("Done setting up OpenGL and window, now entering game loop...");
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			update();
			render();
			
			/**
			 * Here we actually update the screen's pixels, followed by slowing the rendering down to our desired FPS.
			 */
			Display.update();
			Display.sync(25);
		}
		
		/**
		 * Once we reach this point we are already closing down the game, so we tell the world to prepare to shutdown.
		 */
		Game.LOG.info("It was a pleasure meeting you. See you next time!");
		Game.LOG.info("Disposing of world...");
		world.dispose();
		
		/**
		 * This destroys the display, deleting the window.
		 */
		Game.LOG.info("Destroying display...");
		Display.destroy();
		
		/**
		 * Exits the application.
		 */
		Game.LOG.info("Exiting game...");
		System.exit(0);
	}
	
	/**
	 * Renders the game.
	 */
	public void render(){
		/**
		 * This clears the color and depth buffer.
		 */
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		/**
		 * This resets any pre-loaded matrix.
		 */
		GL11.glLoadIdentity();
		
		TextRenderer.drawString("X: " + world.entityManager.getEntity(0).getLocation().getX(), 5, Display.getHeight() - 10, RGBA.WHITE);
		TextRenderer.drawString("Y: " + world.entityManager.getEntity(0).getLocation().getY(), 5, Display.getHeight() - 25, RGBA.WHITE);
		TextRenderer.drawString("World: " + world.entityManager.getEntity(0).getLocation().getWorld().getName(), 5, Display.getHeight() - 40, RGBA.WHITE);
		TextRenderer.drawString("FPS: " + FPS.get(), 5, Display.getHeight() - 55, RGBA.WHITE);
		TextRenderer.drawString("Life: " + ((LivingEntity)world.entityManager.getEntity(0)).getLife(), 5, Display.getHeight() - 70, RGBA.WHITE);
		
		if(((LivingEntity)world.entityManager.getEntity(0)).alive()){
			/**
			 * This saves the matrix so we can alter some stuff.
			 */
			GL11.glPushMatrix();
			
			/**
			 * This draws the player.
			 */
			world.render();
			
			/**
			 * Pops the matrix off the stack, applying our changes and saving the other matrices.
			 */
			GL11.glPopMatrix();
		}else if(!((LivingEntity)world.entityManager.getEntity(0)).alive()){
			TextRenderer.drawString("Game Over", Display.getWidth() / 2 - 40, Display.getHeight() / 2 -10, RGBA.BLACK);
			TextRenderer.drawString("Thanks for playing", Display.getWidth() / 2 - 80, Display.getHeight() / 2 - 30, RGBA.BLACK);
		}
	}
	
	/**
	 * Updates the game.
	 */
	public void update(){
		world.update(getDelta());
		
		FPS.update(getDelta());
	}
	
	/**
	 * Get the system time.
	 * @return The system time.
	 */
	private static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	/**
	 * Get the time since the last frame.
	 * @return The time elapsed since the last frame.
	 */
	private static float getDelta() {
		long currentTime = getTime();
		double delta = (double) currentTime - (double) lastFrame;
		lastFrame = getTime();
		return (float)delta;
	}
	
	public static World getWorld(String name){
		return world;
	}
}