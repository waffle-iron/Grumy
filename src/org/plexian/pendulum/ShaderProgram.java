/**
 * Copyright Plexian Studios, 2014-2016. All Rights Reserved.
 * Released under Plexian License (see plexian.org/license).
 * If unable to obtain license, please email support@plexian.org.
 */
package org.plexian.pendulum;

import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;

public class ShaderProgram {
	private int program, vShader, fShader;
	
	/**
	 * @param vShader The ID of the vertex shader.
	 * @param fShader The ID of the fragment shader.
	 */
	public ShaderProgram(int vShader, int fShader){
		this.vShader = vShader;
		this.fShader = fShader;
		
		createShaderProgram();
	}
	
	/**
	 * This makes the appropriate OpenGL calls in order to create the shader.
	 */
	private void createShaderProgram(){
		program = glCreateProgram();
		
		glAttachShader(program, vShader);
		glAttachShader(program, fShader);
		glLinkProgram(program);
		glValidateProgram(program);
	}
	
	/**
	 * Tells OpenGL to use this shader program.
	 */
	public void use(){
		glUseProgram(program);
	}

	/**
	 * Tell OpenGL to stop using this shader program.
	 */
	public void release(){
		glUseProgram(0);
	}

	/**
	 * Tell OpenGL to delete this program.
	 */
	public void dispose(){
		glDeleteProgram(program);
	}

	/**
	 * Get the id of the shader program.
	 * @return The ID of the shader program.
	 */
	public int getProgram() {
		return program;
	}
}