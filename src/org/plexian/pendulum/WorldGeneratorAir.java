package org.plexian.pendulum;

public class WorldGeneratorAir implements Generator {

	@Override
	public int[][] generateChunk(int[][] tiles, double posX, double posY) {
		if((int)((int)posY / Game.CHUNK_SIZE) >= (int)Game.WORLD_TOP){
			for(int x = 0; x < tiles.length; x++){
				for(int y = 0; y < tiles[x].length; y++){
					tiles[x][y] = Tile.AIR.getId();
				}
			}
		}
		
		return tiles;
	}

	@Override
	public int[][] generateWorld(int[][] tiles) {
		for(int x = 0; x < tiles.length; x++){
			for(int y = tiles.length - (int)Game.CHUNK_SIZE; y < tiles[x].length; y++){
				tiles[x][y] = Tile.AIR.getId();
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
