package org.plexian.pendulum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class LauncherConnectionManager {
	private String server;
	
	public LauncherConnectionManager(String serverURL){
		this.server = serverURL;
	}

	/**
	 * Issue a GET request to the server.
	 * @param query The URL-encoded query to send along with the request.
	 * @return The result.
	 */
	public String issueGetRequest(String query){
		URL url;
		String result = "";
		
		try {
			url = new URL(server + "?" + query);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String strTemp = "";
			
			while (null != (strTemp = br.readLine())) {
				result = strTemp;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
