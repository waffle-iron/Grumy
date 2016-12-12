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

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.plexian.grumy.Game;
import org.plexian.grumy.math.Vector2d;
import org.plexian.grumy.opengl.RGBA;

public class ParticleFactory{
	private ArrayList<Particle> particles;
	private boolean alternating = true;
	private Vector2d rootPos;
	
	public ParticleFactory(Vector2d rootPos){
		this.rootPos = rootPos;
		this.particles = new ArrayList<Particle>();
	}
	
	public void addParticle(){
		double dx, dy, size = Game.TILE_SIZE / 4;
		int life = 100;
		
		if(alternating){
			dx = Math.random() * 5;
			dy = Math.random() * 5;
		}else{
			dx = Math.random() * -5;
			dy = Math.random() * -5;
		}
		
		particles.add(new Particle(this.rootPos.getX() + (Math.random() * 3), this.rootPos.getY() + (Math.random() * 3), dx, dy, size, life, new RGBA(255, 255, 0, 255)));
		alternating = !alternating;
	}
	
	public void update(){
	}
	
	public void render(){
		for(Particle p : particles){
			if(!p.update()){
				p.render();
			}
		}
	}
}