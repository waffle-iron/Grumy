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

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

import org.plexian.grumy.Game;
import org.yaml.snakeyaml.Yaml;

/**
 * Provides the methods and required stuff to use YAML as a configuration
 * format.
 * 
 * @author walt
 * @since 0.3
 */
public class YAMLConfiguration extends FileConfiguration {
    /**
     * This is the parser we use for processing the YAML input.
     */
    private Yaml parser;

    /**
     * This is the root configuration section, its children are the other
     * sections of the configuration. Essentially everything is handled here.
     */
    private ConfigurationSection rootSection;

    /**
     * Create a new YAML configuration from file fileName.
     * 
     * @param fileName
     *            The name of the file.
     */
    public YAMLConfiguration(String fileName) {
        this.parser = new Yaml();

        /**
         * This creates a HashMap based on the data that the parser gives us.
         */
        HashMap<String, Object> dataMap = (HashMap<String, Object>) this.parser
                .load(this.getConfigurationAsStream(fileName));

        /**
         * This looks a little complicated because of the ?: sequence, but
         * essentially: If the data we read from the configuration file is null
         * (empty), than just create a new HashMap. If the data we read from the
         * configuration file isn't null, than use it.
         */
        this.rootSection = new ConfigurationSection((dataMap != null ? dataMap : new HashMap<String, Object>()));
        this.data = this.parser.dump(this.rootSection.dataMap);

        // save();
    }

    public YAMLConfiguration(String fileName, File configDirectory) {
        this.parser = new Yaml();

        /**
         * This creates a HashMap based on the data that the parser gives us.
         */
        HashMap<String, Object> dataMap = (HashMap<String, Object>) this.parser
                .load(this.getConfigurationAsStream(fileName, configDirectory));

        /**
         * This looks a little complicated because of the ?: sequence, but
         * essentially: If the data we read from the configuration file is null
         * (empty), than just create a new HashMap. If the data we read from the
         * configuration file isn't null, than use it.
         */
        this.rootSection = new ConfigurationSection((dataMap != null ? dataMap : new HashMap<String, Object>()));
        this.data = this.parser.dump(this.rootSection.dataMap);

    }

    /**
     * Get the root section of this configuration.
     * 
     * @return The root section.
     */
    public ConfigurationSection getRootSection() {
        return rootSection;
    }

    /**
     * Save the configuration to the filename passed in constructor.
     */
    @Override
    public void save() {
        Yaml yaml = new Yaml();

        /**
         * We delete, than create a new file to ensure that we aren't just
         * adding onto the old one.
         */
        try {
            this.configFile.delete();
            this.configFile.createNewFile();

            FileWriter writer = new FileWriter(this.configFile);

            yaml.dump(this.rootSection.dataMap, writer);
        } catch (Exception e) {
            Game.LOG.warning("Exception: " + e.getMessage());
        }
    }
}
