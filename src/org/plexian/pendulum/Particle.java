package org.plexian.pendulum;

import org.lwjgl.opengl.GL11;

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
