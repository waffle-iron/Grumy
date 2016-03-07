package org.plexian.pendulum;

public enum LauncherMessage {
	ERROR("Whoops! There was an error. The best way to move on is to try again."),
	SERVER_ERROR("Whoops! There was a glitch in our servers. Try again later :("),
	INVALID_LOGIN("Darn! It seems there isn't any player with that login :("),
	VALID_LOGIN("Nice job! Thanks for playing!");
	
	private String message;
	
	LauncherMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return this.message;
	}
}
