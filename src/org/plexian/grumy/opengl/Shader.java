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

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glShaderSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.plexian.grumy.Game;

public class Shader {
    private String vertexLoc, fragmentLoc;
    private int vShader, fShader;

    /**
     * @param vertexLoc
     *        The location of the vertex shader.
     * @param fragmentLoc
     *        The location of the fragment shader.
     */
    public Shader(String vertexLoc, String fragmentLoc) {
        this.vertexLoc = vertexLoc;
        this.fragmentLoc = fragmentLoc;

        loadShader();
    }

    /**
     * Loads shader.
     */
    private void loadShader() {
        vShader = glCreateShader(GL_VERTEX_SHADER);
        fShader = glCreateShader(GL_FRAGMENT_SHADER);

        StringBuilder vSource = new StringBuilder();
        StringBuilder fSource = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(Game.class.getClassLoader().getResourceAsStream(vertexLoc)));

            String line;
            while ((line = reader.readLine()) != null) {
                vSource.append(line).append('\n');
            }
            reader.close();
        } catch (IOException e) {
            System.err.println(vSource);
            System.err.println("Error loading vertex shader source at Shader.java location: " + vertexLoc);
        }

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(Game.class.getClassLoader().getResourceAsStream(fragmentLoc)));
            String line;
            while ((line = reader.readLine()) != null) {
                fSource.append(line).append('\n');
            }
            reader.close();
        } catch (IOException e) {
            System.err.println(fSource);
            System.err.println("Error loading fragment shader source at Shader.java location: " + fragmentLoc);
        }

        compileShaders(vSource, fSource);
    }

    /**
     * @param vSource
     *        Source of Vertex shader.
     * @param fSource
     *        Source of Fragment shader.
     */
    private void compileShaders(StringBuilder vSource, StringBuilder fSource) {
        glShaderSource(vShader, vSource);
        glCompileShader(vShader);
        if (glGetShaderi(vShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println(glGetShaderInfoLog(vShader, 1024));
            System.err.println("Error compiling vertex shader at Shader.java location: " + vertexLoc);
        }

        glShaderSource(fShader, fSource);
        glCompileShader(fShader);
        if (glGetShaderi(fShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println(glGetShaderInfoLog(fShader, 1024));
            System.err.println("Error compiling fragment shader at Shader.java location: " + fragmentLoc);
        }
    }

    /**
     * @return ID of the Vertex shader.
     */
    public int getvShader() {
        return vShader;
    }

    /**
     * @return ID of the Fragment shader.
     */
    public int getfShader() {
        return fShader;
    }

    public void dispose() {
        glDeleteShader(vShader);
        glDeleteShader(fShader);
    }
}