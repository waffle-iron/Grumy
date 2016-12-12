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
package org.plexian.grumy.configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;

import org.plexian.grumy.Game;

/**
 * 
 * @author walt
 * @category Configuration
 */
public abstract class FileConfiguration extends Configuration {
    /**
     * This is an object that points to the folder that holds all our
     * configuration.
     */
    public static File CONFIGURATION_FOLDER = new File(System.getProperty("user.home") + "/Pendulum/", "config/");

    /**
     * This is a file object for the configuration.
     */
    protected File configFile;

    /**
     * This is a string that holds the YAML string.
     */
    protected String data;

    /**
     * Creates a new instance of FileConfiguration, creating the config
     * directory in the process.
     */
    public FileConfiguration() {
        CONFIGURATION_FOLDER.mkdirs();
    }

    /**
     * Gets the given configuration as a File.
     * 
     * @param s
     *            The name of the configuration.
     * @return A file object to the file, or CONFIGURATION_FOLDER if given
     *         filename is a directory.
     */
    public File getConfiguration(String s) {
        File f = new File(FileConfiguration.CONFIGURATION_FOLDER, s);

        if (f.isDirectory()) {
            return CONFIGURATION_FOLDER;
        }

        try {
            // f.delete();
            f.createNewFile();
        } catch (Exception e) {
            Game.LOG.info("Exception: " + e.getMessage());
        }

        this.configFile = f;

        return f;
    }

    public File getConfiguration(String s, File directory) {
        File f = new File(directory, s);

        if (f.isDirectory()) {
            return directory;
        }

        try {
            // f.delete();
            f.createNewFile();
        } catch (Exception e) {
            Game.LOG.info("Exception: " + e.getMessage());
        }

        this.configFile = f;

        return f;
    }

    /**
     * Reads a configuration file into a string which can be pumped into a
     * parser.
     * 
     * @param s
     *            The name of the file to read.
     * @return The contents of the file, or "" if no such file/directory.
     */
    public String loadConfiguration(String s) {
        File f = getConfiguration(s);
        String everything = "";

        if (f.isDirectory()) {
            return "";
        }

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(f));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            everything = sb.toString();

            br.close();
        } catch (Exception e) {
            Game.LOG.warning("Exception: " + e.getMessage());
        }

        return everything;
    }

    public String loadConfiguration(String s, File directory) {
        File f = getConfiguration(s, directory);
        String everything = "";

        if (f.isDirectory()) {
            return "";
        }

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(f));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            everything = sb.toString();

            br.close();
        } catch (Exception e) {
            Game.LOG.warning("Exception: " + e.getMessage());
        }

        return everything;
    }

    /**
     * Gets the configuration as an input stream, reading from a file.
     * 
     * @param s
     *            The configuration name.
     * @return The input stream if file exists, or null if not.
     */
    public InputStream getConfigurationAsStream(String s) {
        InputStream in = null;

        try {
            in = new FileInputStream(getConfiguration(s));
        } catch (Exception e) {
            Game.LOG.warning("Exception: " + e.getMessage());
        }

        return in;
    }

    public InputStream getConfigurationAsStream(String s, File directory) {
        InputStream in = null;

        try {
            in = new FileInputStream(getConfiguration(s, directory));
        } catch (Exception e) {
            Game.LOG.warning("Exception: " + e.getMessage());
        }

        return in;
    }

    /**
     * Saves configuration to file.
     */
    public void save() {
        try {
            FileWriter fileWriter = new FileWriter(this.configFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(this.data);
        } catch (Exception e) {
            Game.LOG.warning("Exception: " + e.getMessage());
        }
    }
}