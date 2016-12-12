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
package org.plexian.grumy.launcher;

import java.io.File;

/**
 * Provides the necessary utilities for making Pendulum platform-dependent.
 * 
 * @author walt
 * @since 0.3
 */
public class OSUtils {
    /**
     * Provides a few enums to allow for easy storage of OS types.
     * 
     * @author walt
     */
    public enum OSType {
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
         * 
         * @param id
         *            The id of the OS type.
         */
        OSType(int id) {
            this.id = id;
        }

        /**
         * Get the type id of the OS.
         * 
         * @return The type id of the OS.
         */
        public int getId() {
            return id;
        }
    }

    /**
     * Detect the type of OS the system is running.
     * 
     * @return An OSType enum of the OS.
     */
    public static OSType detectOS() {
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
    public static void loadNatives() {
        if (detectOS() == OSType.WINDOWS) {
            System.setProperty("org.lwjgl.librarypath", new File("lib/lwjgl/native/windows").getAbsolutePath());
        } else if (detectOS() == OSType.MAC) {
            System.setProperty("org.lwjgl.librarypath", new File("lib/lwjgl/native/mac").getAbsolutePath());
        } else if (detectOS() == OSType.LINUX) {
            System.setProperty("org.lwjgl.librarypath", new File("lib/lwjgl/native/linux").getAbsolutePath());
        } else if (detectOS() == OSType.SUN) {
            System.setProperty("org.lwjgl.librarypath", new File("lib/lwjgl/native/sun").getAbsolutePath());
        }
    }
}
