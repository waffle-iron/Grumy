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

public class SpriteSheet {
    public static SpriteSheet tileTextures = new SpriteSheet("textures/default.png", 32);

    private Texture texture;
    private String path;
    private float size;

    /**
     * @param path
     *            Path to image.
     * @param size
     *            Size in pixels of all textures. (eg. 8, 16, 32, 64...).
     */
    public SpriteSheet(String path, float size) {
        this.path = path;
        this.size = 1f / size;

        load();
    }

    /**
     * Load the image as a texture.
     */
    private void load() {
        texture = Texture.loadTexture(path);
    }

    /**
     * Bind the spritesheet to OpenGL.
     */
    public void bind() {
        texture.bind();
    }

    /**
     * Unbind the sheet from OpenGL.
     */
    public void unbind() {
        texture.unbind();
    }

    /**
     * Delete the sheet as a texture.
     */
    public void delete() {
        texture.delete();
    }

    /**
     * Get the size of the textures in OpenGL floats, not pixel sizes.
     * 
     * @return Size of the textures.
     */
    public float uniformSize() {
        return size;
    }
}