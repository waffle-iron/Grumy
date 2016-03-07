package org.plexian.pendulum;

public enum WorldType {
	CLASSIC(1),
	SAND(2),
	AIR(3),
	SPACE(4);
	
	private int id;
	
	WorldType(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}

	public static WorldType getType(String s){
		switch(s){
		case "classic":
			return WorldType.CLASSIC;
		case "sand":
			return WorldType.SAND;
		case "air":
			return WorldType.AIR;
		case "space":
			return WorldType.SPACE;
		default: return WorldType.CLASSIC;
		}
	}
	
	public String toString(){
		return name().toLowerCase();
	}
}
