package org.plexian.pendulum;

import java.io.File;

/**
 * Provides the necessary utilities for making Pendulum platform-dependent.
 * @author walt
 * @since 0.3
 */
public class OSUtils {
	/**
	 * Provides a few enums to allow for easy storage of OS types.
	 * @author walt
	 */
	public enum OSType{
		/**
		 * An unknown type.
		 */
		OTHER(0),
		
		/**
		 * A computer running OSX.
		 */
		MAC(1),
		
		/**
		 * A computer running Windows.
		 */
		WINDOWS(2),
		
		/**
		 * A computer running Linux/Ubuntu
		 */
		LINUX(3),
		
		/**
		 * A computer running Sun system.
		 */
		SUN(4);
		
		/**
		 * This is just an easy way to store the OS type id.
		 */
		int id;
		
		/**
		 * Constructor for an OSType.
		 * @param id The id of the OS type.
		 */
		OSType(int id){
			this.id = id;
		}
		
		/**
		 * Get the type id of the OS.
		 * @return The type id of the OS.
		 */
		public int getId(){
			return id;
		}
	}
	
	/**
	 * Detect the type of OS the system is running.
	 * @return An OSType enum of the OS.
	 */
	public static OSType detectOS(){
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
		    // Windows
			return OSType.WINDOWS;
		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
		    // Mac OS X
			return OSType.MAC;
		} else if (System.getProperty("os.name").toLowerCase().contains("linux")) {
		    // Linux
			return OSType.LINUX;
		} else if (System.getProperty("os.name").toLowerCase().contains("sun")) {
		    // SunOS (Solaris)
			return OSType.SUN;
		} else {
		    throw new RuntimeException("Your OS is not supported");
		}
	}
	
	/**
	 * Load the native files for the host system.
	 */
	public static void loadNatives(){
		if(detectOS() == OSType.WINDOWS){
			System.setProperty("org.lwjgl.librarypath", new File("assets/native/windows").getAbsolutePath());
		}else if(detectOS() == OSType.MAC){
			System.setProperty("org.lwjgl.librarypath", new File("assets/native/mac").getAbsolutePath());
		}else if(detectOS() == OSType.LINUX){
			System.setProperty("org.lwjgl.librarypath", new File("assets/native/linux").getAbsolutePath());
		}else if(detectOS() == OSType.SUN){
			System.setProperty("org.lwjgl.librarypath", new File("assets/native/sun").getAbsolutePath());
		}
	}
}
