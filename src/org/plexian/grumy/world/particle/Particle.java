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
import org.plexian.grumy.opengl.RGBA;

public class Particle {
	private double x, y, dx, dy, size;
	private int life;
	private RGBA color;
	
	public Particle(double x, double y, double dx, double dy, double size, int life, RGBA color){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.size = size;
		this.life = life;
		this.color = color;
	}
	
	public boolean update(){
		x += dx;
		y += dy;
		
		life--;
		
		if(life <= 0){
			dx = 0;
			dy = 0;
			return true;
		}
		
		return false;
	}
	
	public void render(){
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor3f(color.getR(), color.getG(), color.getB());
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2d(x, y);
			GL11.glVertex2d(x, y + size);
			GL11.glVertex2d(x + size, y + size);
			GL11.glVertex2d(x + size, y);
		GL11.glEnd();
	//	GL11.glColor3f(0, 0, 0);
		GL11.glEnable(GL11.GL_BLEND);
	}
}
