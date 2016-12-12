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
package org.plexian.grumy.world.particle;

import org.lwjgl.opengl.GL11;
import org.plexian.grumy.Game;
import org.plexian.grumy.opengl.RGBA;

public class ParticleRenderer {
	public static void drawParticle(double x, double y, double size, RGBA color){
		x *= Game.TILE_SIZE;
		y *= Game.TILE_SIZE;
		size *= Game.TILE_SIZE;

		GL11.glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2d(x, y);
			GL11.glVertex2d(x, y + size);
			GL11.glVertex2d(x + size, y + size);
			GL11.glVertex2d(x + size, y);
		GL11.glEnd();
		GL11.glColor4f(1, 1, 1, 0);
	}
}
