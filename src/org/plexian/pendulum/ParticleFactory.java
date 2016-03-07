package org.plexian.pendulum;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

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