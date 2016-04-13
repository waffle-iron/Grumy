package org.plexian.pendulum;

public enum GameMode {
	NORMAL(0),
	LEVEL_EDITOR(1);
	
	int id;
	GameMode(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static GameMode getById(int id){
		for(GameMode gm : GameMode.values()){
			if(gm.getId() == id){
				return gm;
			}
		}
		
		return GameMode.NORMAL;
	}
}
