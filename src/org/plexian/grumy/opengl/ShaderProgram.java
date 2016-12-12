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
package org.plexian.grumy.opengl;

import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;

public class ShaderProgram {
    private int program, vShader, fShader;

    /**
     * @param vShader
     *            The ID of the vertex shader.
     * @param fShader
     *            The ID of the fragment shader.
     */
    public ShaderProgram(int vShader, int fShader) {
        this.vShader = vShader;
        this.fShader = fShader;

        createShaderProgram();
    }

    /**
     * This makes the appropriate OpenGL calls in order to create the shader.
     */
    private void createShaderProgram() {
        program = glCreateProgram();

        glAttachShader(program, vShader);
        glAttachShader(program, fShader);
        glLinkProgram(program);
        glValidateProgram(program);
    }

    /**
     * Tells OpenGL to use this shader program.
     */
    public void use() {
        glUseProgram(program);
    }

    /**
     * Tell OpenGL to stop using this shader program.
     */
    public void release() {
        glUseProgram(0);
    }

    /**
     * Tell OpenGL to delete this program.
     */
    public void dispose() {
        glDeleteProgram(program);
    }

    /**
     * Get the id of the shader program.
     * 
     * @return The ID of the shader program.
     */
    public int getProgram() {
        return program;
    }
}