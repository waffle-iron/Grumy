package org.plexian.pendulum;

public enum Material {
	AIR("air", Tile.AIR, 0),
	KEY("key", Tile.ITEM_KEY, 1);
	
	private String name;
	private Tile displayTile;
	private int materialId;
	
	Material(String name, Tile displayTile, int materialId){
		this.name = name;
		this.displayTile = displayTile;
		this.materialId = materialId;
	}
	
	public String getName(){
		return name;
	}
	
	public Tile getDisplayTile(){
		return displayTile;
	}
	
	public int getMaterialId(){
		return this.materialId;
	}
}
