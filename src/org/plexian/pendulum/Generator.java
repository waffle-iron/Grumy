package org.plexian.pendulum;

public interface Generator {
	public int[][] generateChunk(int[][] tiles, double posX, double posY);
	public int[][] generateWorld(int[][] tiles);
	public int[][] generateChunk(Chunk c);
	public int[][] generateWorld(World w);
}
