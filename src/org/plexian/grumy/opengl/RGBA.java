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

public class RGBA {
    public static RGBA WHITE = new RGBA(255, 255, 255, 1);
    public static RGBA BLACK = new RGBA(0, 0, 0, 1);
    public static RGBA BLUE = new RGBA(0, 0, 1, 1);

    private float r, g, b, a;

    public static RGBA parse(String color) {
        String[] split = color.split(",");
        String r = split[0], g = split[1], b = split[2], a = split[3];

        return new RGBA(Float.parseFloat(r), Float.parseFloat(g), Float.parseFloat(b), Float.parseFloat(a));
    }

    public RGBA(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public float getA() {
        return a;
    }
}
