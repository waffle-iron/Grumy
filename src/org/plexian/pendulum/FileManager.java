package org.plexian.pendulum;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.plexian.pendulum.Chunk.ChunkState;

/**
 * The chunk and world loader and saver.
 * @author walt
 * @since 0.3
 */
public class FileManager {
	/**
	 * This is a File object pointing to where Pendulum is located.
	 */
	private File gameDirectory;
	
	/**
	 * This is a File object pointing to where the current world is saved to.
	 */
	private File currentWorldDirectory;

	private World world;
	
	/**
	 * This method creates a new instance of FileManager, allow for the creation of save files, loading of save files,
	 * and saving of chunks.
	 * @param w The world that contains the chunks we will be saving and loading.
	 */
	public FileManager(World w){
		/**
		 * This just sets up the game directory to be at the user's home directory, in a folder called Pendulum.
		 * On Mac OSX the home directory is /Users/[Username], on Linux it is /home/[Username].
		 */
		this.gameDirectory = new File(System.getProperty("user.home"), "Pendulum/");
		
		/**
		 * Basically, if the game directory doesn't exist, create it.
		 */
		if(!this.gameDirectory.exists()){
			this.gameDirectory.mkdirs();
		}
		
		updateWorldName(w.getName());
	}
	
	public void updateWorldName(String name){
		/**
		 * This just sets up the current world directory to be inside the Pendulum folder, in a folder titled by the
		 * world's name.
		 */
		this.currentWorldDirectory = new File(this.gameDirectory, name + "/");
		
		/**
		 * Basically, if the world directory doesn't exist, create it.
		 */
		if(!this.currentWorldDirectory.exists()){
			this.currentWorldDirectory.mkdirs();
		}
	}
	
	/**
	 * Saves a chunk to disk.
	 * @param c The chunk to save.
	 */
	public void saveChunk(Chunk c){
		if(c.getState() == ChunkState.BUILDING || c.getState() == ChunkState.DELETING){
			return;
		}
		
		c.setState(ChunkState.SAVING);
		
		/**
		 * We use a try-catch block to ensure that any error's are properly dealt with.
		 */
		try {
			/**
			 * This simply creates a new file to save the chunk to.
			 * It is located inside the current world directory, with a filename of x-y where x=c.position.x / 16, and y=c.position.y / 16.
			 */
			File outDir = new File(this.currentWorldDirectory + System.getProperty("file.separator") + (int)(c.getPosition().getX() / Game.CHUNK_SIZE) + "-" + (int)(c.getPosition().getY() / Game.CHUNK_SIZE) + ".chk");

			/**
			 * Deletes the file if it exists to ensure that we only have the latest copy of the chunk saved.
			 */
            outDir.delete();
            
            /**
             * Creates a new file.
             */
            outDir.createNewFile();
            
            /**
             * We setup a new output stream to send all our data to the file.
             */
            DataOutputStream outStream = new DataOutputStream(new FileOutputStream(outDir));
            
            /**
             * We loop through all the tiles in the chunk, and write their id's to the file.
             */
            for( int x = 0; x < Game.CHUNK_SIZE; x++ ) {
                for( int y = 0; y < Game.CHUNK_SIZE; y++ ) {
                	outStream.writeInt(c.getTile(x, y));
                }
            }

            /**
             * We then close the file to prevent any dirty data from seeping in.
             */
            outStream.close();
        } catch (Exception e) {
        	/**
        	 * If there was any error (probably an I/O error), print out the cause.
        	 */
            e.printStackTrace();
        }
		
		c.setState(ChunkState.PASSIVE);
	}
	
	/**
	 * Load a chunk from a file on disk.
	 * @param x The X-coordinate for the chunk.
	 * @param y The Y-coordinate for the chunk.
	 * @return The chunk that was saved on disk.
	 */
	public Chunk loadChunk(int x, int y){
		/**
		 * We create a new instance of File, located at the current world directory with filename: x-y.chk where x=chunk.position.x/16, y=chunk.position.y/16
		 */
		File outDir = new File(this.currentWorldDirectory + System.getProperty("file.separator") + x + "-" + y + ".chk");
		
		/**
		 * Create a new empty chunk at position x, and y.
		 */
		Chunk c = new Chunk(x, y, (Game.LEVEL_BUILDER == true ? 0 : 1));
		
		/**
		 * We lock 
		 */
		c.setState(ChunkState.LOADING);
		
		/**
		 * While this seems kind of pointless, its actually not. Trust me. The reason we don't initialize it outside the try
		 * loop is because it throws an error, and we want to handle that in case it does happen.
		 */
		DataInputStream in = null;
	
		/**
		 * We put this in a try-catch block to handle any errors that occur.
		 */
		try{
			/**
			 * We create a new data stream to the chunk file.
			 */
			in = new DataInputStream(new FileInputStream(outDir));
			
			/**
			 * We loop through the number of tiles in a chunk, essentially casting a new chunk from the 
			 * file. I'm feeling like a blacksmith!!
			 */
			for(int x1 = 0; x1 < Game.CHUNK_SIZE; x1++){
				for(int y1 = 0; y1 < Game.CHUNK_SIZE; y1++){
					/**
					 * We read the tile id in from the file and store it as an integer.
					 */
					int i =  in.readInt();
					
					/**
					 * Set the tile in the chunk at (x, y) to the read-in id. Nifty huh?
					 */
					c.setTile(x1, y1, i);
				}
			}
			
			/**
			 * We need to rebuild the chunk's display list for our changes to take effect in the rendering aspect, so thats what we do.
			 */
			c.rebuild();
			
			/**
			 * We close the file to ensure quality security. Our #1 goal here at Oscorp.
			 */
			in.close();
		}catch(Exception e){
			/**
			 * If there was an exception, it was caused by the FileReader hitting the end of the file, so we just ignore it.
			 */
		}
		
		/**
		 * Return the chunk we just created.
		 */
		return c;
	}
	
	public YAMLConfiguration getConfiguration(){
		YAMLConfiguration config = new YAMLConfiguration("config.yml", this.currentWorldDirectory);
		
		return config;
	}
}