package org.plexian.pendulum;

import org.lwjgl.opengl.GL11;

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
