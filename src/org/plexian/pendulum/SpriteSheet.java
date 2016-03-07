/**
 * Copyright Plexian Studios, 2014-2016. All Rights Reserved.
 * Released under Plexian License (see plexian.org/license).
 * If unable to obtain license, please email support@plexian.org.
 */
package org.plexian.pendulum;

public class SpriteSheet {
	public static SpriteSheet tileTextures = new SpriteSheet("Textures.png", 32);
	
	private Texture texture;
	private String path;
	private float size;
	
	/**
	 * @param path Path to image.
	 * @param size Size in pixels of all textures. (eg. 8, 16, 32, 64...).
	 */
	public SpriteSheet(String path, float size){
		this.path = path;
		this.size = 1f / size;
		
		load();
	}
	
	/**
	 * Load the image as a texture.
	 */
	private void load(){
		texture = Texture.loadTexture(path);
	}
	
	/**
	 * Bind the spritesheet to OpenGL.
	 */
	public void bind(){
		texture.bind();
	}
	
	/**
	 * Unbind the sheet from OpenGL.
	 */
	public void unbind(){
		texture.unbind();
	}
	
	/**
	 * Delete the sheet as a texture.
	 */
	public void delete(){
		texture.delete();
	}
	
	/**
	 * Get the size of the textures in OpenGL floats, not pixel sizes.
	 * @return Size of the textures.
	 */
	public float uniformSize(){
		return size;
	}
}