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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class LauncherConnectionManager {
    private String server;

    public LauncherConnectionManager(String serverURL) {
        this.server = serverURL;
    }

    /**
     * Issue a GET request to the server.
     * 
     * @param query
     *            The URL-encoded query to send along with the request.
     * @return The result.
     */
    public String issueGetRequest(String query) {
        URL url;
        String result = "";

        return "valid";
        /**
         * Todo: When server API is up and running uncomment this.
         *
         * try { url = new URL(server + "?" + query); BufferedReader br = new
         * BufferedReader(new InputStreamReader(url.openStream())); String
         * strTemp = "";
         * 
         * while (null != (strTemp = br.readLine())) { result = strTemp; }
         * 
         * } catch (Exception e) { e.printStackTrace(); }
         * 
         * return result;
         */
    }
}
