package org.plexian.pendulum;

public class WorldGeneratorTree implements Generator {

	@Override
	public int[][] generateChunk(int[][] tiles, double posX, double posY) {
		boolean leave = false;
		
		for(int x = 0; x < tiles.length - 2; x++){
			for(int y = 0; y < tiles.length - 4; y++){
				for(int x1 = 0; x1 < 3; x1++){
					for(int y1 = 0; y1 < 5; y1++){
						if(tiles[x + x1][y + y1] == Tile.AIR.getId() && tiles[x + x1][y + y1 - (y1 > 0 ? 1 : 0)] == Tile.AIR.getId()){
							tiles[x + x1][y + y1] = Tile.AIR.getId();
						}
					}
				}
			}
		}
		
		return tiles;
	}

	@Override
	public int[][] generateWorld(int[][] tiles) {
		for(int x = 0; x < tiles.length - 2; x++){
			for(int y = 0; y < tiles.length - 4; y++){
				for(int x1 = 0; x1 < 3; x1++){
					for(int y1 = 0; y1 < 5; y1++){
						if(tiles[x + x1][y + y1] == Tile.AIR.getId() && tiles[x + x1][y + y1 - (y1 > 0 ? 1 : 0)] == Tile.AIR.getId()){
							tiles[x + x1][y + y1] = Tile.AIR.getId();
						}
					}
				}
			}
		}
		
		return tiles;
	}

	@Override
	public int[][] generateChunk(Chunk c) {
		return c.getTiles();
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
