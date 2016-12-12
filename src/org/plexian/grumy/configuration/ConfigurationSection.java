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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides all the methods needed for sections of configuration/whole
 * configurations.
 * 
 * @author walt
 * @category Configuration
 */
public class ConfigurationSection extends Configuration {
    /**
     * This map stores all the data for the configuration.
     */
    public Map<String, Object> dataMap = new HashMap<String, Object>();

    /**
     * Create a new ConfigurationSection
     * 
     * @param dataMap
     *            The initial values for the data map.
     */
    public ConfigurationSection(Map<String, Object> dataMap) {
        this.dataMap.putAll(dataMap);
    }

    /**
     * Gets the deepest section in the key. Note that you use an object's key,
     * not a configuration section, otherwise you will get the second-deepest
     * section.
     * 
     * @param key The key to search through.
     * @return The deepest section in the key.
     */
    public ConfigurationSection getDeepestSection(String key) {
        /**
         * We split up the key into its components.
         */
        String[] paths = key.split("\\.");

        /**
         * We need two sections, one for the last one that we used, and one for
         * the current section we are in.
         */
        ConfigurationSection lastSection = this;
        ConfigurationSection currentSection = this;

        /**
         * Here we loop through all the paths, moving downwards until we reach
         * the second-to-last object.
         */
        for (int i = 0; i < paths.length - 1; i++) {
            currentSection = lastSection.getConfigurationSection(paths[i]);
        }

        return currentSection;
    }

    /**
     * Get an integer at position $key.
     * 
     * @param key
     *            The location of the integer.
     * @return The integer at location $key or null.
     */
    public int getInt(String key) {
        /**
         * Basically if the user want's to fetch: users.walt.id, we need to
         * descend all the way down to object 'walt' before looking up the
         * object.
         */
        if (key.contains(".")) {
            String[] paths = key.split("\\.");

            /**
             * We then fetch the object.
             */
            return (Integer) getDeepestSection(key).getInt(paths[paths.length - 1]);
        }

        /**
         * No .s in the path, so we just directly lookup the object.
         */
        return (Integer) this.dataMap.get(key);
    }

    /**
     * Get a double at position $key.
     * 
     * @param key
     *            The location of the double.
     * @return The double or null.
     */
    public double getDouble(String key) {
        /**
         * Basically if the user want's to fetch: users.walt.id, we need to
         * descend all the way down to object 'walt' before looking up the
         * object.
         */
        if (key.contains(".")) {
            String[] paths = key.split("\\.");

            /**
             * We then fetch the object.
             */
            return (Double) getDeepestSection(key).getDouble(paths[paths.length - 1]);
        }

        /**
         * No .s in the path, so we just directly lookup the object.
         */
        return (Double) this.dataMap.get(key);
    }

    /**
     * Get a float at position $key
     * 
     * @param key
     *            The location of the float.
     * @return The float at locaiton $key.
     */
    public float getFloat(String key) {
        /**
         * Basically if the user want's to fetch: users.walt.id, we need to
         * descend all the way down to object 'walt' before looking up the
         * object.
         */
        if (key.contains(".")) {
            String[] paths = key.split("\\.");

            /**
             * We then fetch the object.
             */
            return (Float) getDeepestSection(key).getFloat(paths[paths.length - 1]);
        }

        /**
         * No .s in the path, so we just directly lookup the object.
         */
        return (Float) this.dataMap.get(key);
    }

    /**
     * Get a boolean at position $key
     * 
     * @param key
     *            The location of the boolean.
     * @return The boolean at location $key.
     */
    public boolean getBoolean(String key) {
        /**
         * Basically if the user want's to fetch: users.walt.id, we need to
         * descend all the way down to object 'walt' before looking up the
         * object.
         */
        if (key.contains(".")) {
            String[] paths = key.split("\\.");

            /**
             * We then fetch the object.
             */
            return (boolean) getDeepestSection(key).getBoolean(paths[paths.length - 1]);
        }

        /**
         * No .s in the path, so we just directly lookup the object.
         */
        return (boolean) this.dataMap.get(key);
    }

    /**
     * Get a byte at location $key
     * 
     * @param key
     *            The location to get a byte at.
     * @return Byte at location $key.
     */
    public byte getByte(String key) {
        /**
         * Basically if the user want's to fetch: users.walt.id, we need to
         * descend all the way down to object 'walt' before looking up the
         * object.
         */
        if (key.contains(".")) {
            String[] paths = key.split("\\.");

            /**
             * We then fetch the object.
             */
            return (byte) getDeepestSection(key).getByte(paths[paths.length - 1]);
        }

        /**
         * No .s in the path, so we just directly lookup the object.
         */
        return (byte) this.dataMap.get(key);
    }

    /**
     * Get a string at location $key.
     * 
     * @param key
     *            The location to get a string at.
     * @return The string at location $key.
     */
    public String getString(String key) {
        /**
         * Basically if the user want's to fetch: users.walt.id, we need to
         * descend all the way down to object 'walt' before looking up the
         * object.
         */
        if (key.contains(".")) {
            String[] paths = key.split("\\.");

            /**
             * We then fetch the object.
             */
            if (paths.length == 0) {
                return (String) getDeepestSection(key).getString(paths[paths.length]);
            }

            return (String) getDeepestSection(key).getString(paths[paths.length - 1]);
        }

        /**
         * No .s in the path, so we just directly lookup the object.
         */
        return (String) this.dataMap.get(key);
    }

    /**
     * Get a configuration section at location $key.
     * 
     * @param key
     *            The location of the configuration section.
     * @return The configuration section at $key.
     */
    public ConfigurationSection getConfigurationSection(String key) {
        /**
         * Basically if the user want's to fetch: users.walt.id, we need to
         * descend all the way down to object 'walt' before looking up the
         * object.
         */
        if (key.contains(".")) {
            String[] paths = key.split("\\.");

            /**
             * We then fetch the object.
             */
            return new ConfigurationSection((getDeepestSection(key).getMap(paths[paths.length - 1]) != null
                    ? getDeepestSection(key).getMap(paths[paths.length - 1]) : new HashMap<String, Object>()));
        }

        /**
         * No .s in the path, so we just directly lookup the object.
         */
        return new ConfigurationSection((Map<String, Object>) (this.dataMap.get(key) != null ? this.dataMap.get(key)
                : new HashMap<String, Object>()));
    }

    /**
     * Get a map (Map<String, Object>) at location $key.
     * 
     * @param key
     *            The location of the map.
     * @return The map at location $key.
     */
    public Map<String, Object> getMap(String key) {
        /**
         * Basically if the user want's to fetch: users.walt.id, we need to
         * descend all the way down to object 'walt' before looking up the
         * object.
         */
        if (key.contains(".")) {
            String[] paths = key.split("\\.");

            /**
             * We then fetch the object.
             */
            return getDeepestSection(key).getMap(paths[paths.length - 1]);
        }

        /**
         * No .s in the path, so we just directly lookup the object.
         */
        return (Map<String, Object>) this.dataMap.get(key);
    }

    /**
     * Get a list from the data map at location $key.
     * 
     * @param key
     *            The location of the list.
     * @return The list at location $key
     */
    public List<Object> getList(String key) {
        return (List<Object>) this.dataMap.get(key);
    }

    /**
     * Add an object to the data map.
     * 
     * @param key
     *            The key for the map.
     * @param value
     *            The value to put into the map.
     */
    public void put(String key, Object value) {
        /**
         * Basically if the user want's to put: users.walt.id, we need to
         * descend all the way down to object 'walt' before adding the object.
         */
        if (key.contains(".")) {
            String[] paths = key.split("\\.");

            /**
             * We then fetch the object.
             */
            getDeepestSection(key).put(paths[paths.length - 1], value);
        }

        /**
         * No .s in the path, so we just directly lookup the object.
         */
        this.dataMap.put(key, value);
    }

    /**
     * Put a hashmap's contents into the map.
     * 
     * @param key
     *            The location to put the hashmap at.
     * @param map
     *            The map to put.
     */
    public void putHashMap(String key, HashMap<String, Object> map) {
        /**
         * Basically if the user want's to put: users.walt.id, we need to
         * descend all the way down to object 'walt' before adding the object.
         */
        if (key.contains(".")) {
            String[] paths = key.split("\\.");

            /**
             * We then fetch the object.
             */
            getDeepestSection(key).dataMap.putAll(map);
        }

        /**
         * No .s in the path, so we just directly lookup the object.
         */
        this.dataMap.putAll(map);
    }
}
