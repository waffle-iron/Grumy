/**
 * Copyright Plexian Studios, 2014-2016. All Rights Reserved.
 * Released under Plexian License (see plexian.org/license).
 * If unable to obtain license, please email support@plexian.org.
 */
package org.plexian.pendulum;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Texture {
	public int id, width, height;
	
	/**
	 * @param id ID of the texture (used by OpenGL).
	 * @param width Width of texture map.
	 * @param height Height of texture map.
	 */
	private Texture(int id, int width, int height){
		this.id = id;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * @param location Texture file location.
	 * @return New texture from the location.
	 */
	public static Texture loadTexture(String location){
		BufferedImage image = null;
		
		try{
			image = ImageIO.read(Game.class.getClassLoader().getResourceAsStream("assets/" + location));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getHeight());
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);
		
		for(int y = 0; y < image.getHeight(); y++){
			for(int x = 0; x < image.getWidth(); x++){
				int pixel = pixels[y * image.getWidth() + x];
				
				buffer.put((byte)((pixel >> 16) & 0xFF));
				buffer.put((byte)((pixel >> 8) & 0xFF));
				buffer.put((byte)((pixel) & 0xFF));
				buffer.put((byte)((pixel >> 24) & 0xFF));
			}
		}
		
		buffer.flip();
		
		int id = glGenTextures();
		
		glBindTexture(GL_TEXTURE_2D, id);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		
		
		return new Texture(id, image.getWidth(), image.getHeight());
	}
	
	/**
	 * Tell OpenGL to bind the texture, essencially allowing you to use the texture.
	 */
	public void bind(){
		glEnable(GL_TEXTURE_2D);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	/**
	 * Tell OpenGL to unbind/stop using the texture.
	 */
	public void unbind(){
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	/**
	 * Tell OpenGL to delete the texture from its data.
	 */
	public void delete(){
		glDeleteTextures(id);
	}
	
	/**
	 * Get the width of the texture in pixels.
	 * @return The width of the texture in pixels.
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * Get the height of the texture in pixels.
	 * @return The height of the texture in pixels.
	 */
	public int getHeight(){
		return height;
	}
}