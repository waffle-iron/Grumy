package org.plexian.pendulum;

public class WorldGeneratorStone implements Generator {
	private SimplexNoise noise;
	private float frequency;
	
	public WorldGeneratorStone(SimplexNoise noise, float frequency){
		this.noise = noise;
		this.frequency = frequency;
	}
	
	@Override
	public int[][] generateChunk(int[][] tiles, double xPos, double yPos) {
		for(int x = 0; x < tiles.length; x++){
			for(int y = 1; y < tiles[x].length; y++){
				float val = noise.noise(x / frequency + (int)xPos, y / frequency + (int)yPos);
				
				if(val < 0){
					tiles[x][y] = Tile.AIR.getId();
				}else if(val > .5){
					tiles[x][y] = Tile.AIR.getId();
				}
			}
		}
		
		return tiles;
	}

	@Override
	public int[][] generateWorld(int[][] tiles) {
		for(int x = 0; x < tiles.length - 1; x++){
			for(int y = 1; y < tiles[x].length; y++){
				float val = noise.noise(x / frequency, y / frequency);
				
				if(val < 0){
					tiles[x][y] = Tile.AIR.getId();
				}else if(val > .5){
					tiles[x][y] = Tile.AIR.getId();
				}
			}
		}
		
		return tiles;
	}

	@Override
	public int[][] generateChunk(Chunk c) {
		return generateChunk(c.getTiles(), c.getPosition().getX(), c.getPosition().getY());
	}

	@Override
	public int[][] generateWorld(World w) {
		int[][] tiles = new int[w.getChunks().size() * (int)Game.CHUNK_SIZE][w.getChunks().size() * (int)Game.CHUNK_SIZE];
		
		for(Chunk c : w.getChunks()){
			for(int x = 0; x < c.getTiles().length; x++){
				for(int y = 0; y < c.getTiles().length; y++){
					tiles[x + (int)c.getPosition().x][y + (int)c.getPosition().y] = c.getTile(x, y);
				}
			}
		}
		
		return generateWorld(tiles);
	}

}
