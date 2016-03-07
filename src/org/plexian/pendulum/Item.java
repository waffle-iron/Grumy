package org.plexian.pendulum;

import java.util.HashMap;

public abstract class Item {
	private static HashMap<Integer, Item> itemMap = new HashMap<Integer, Item>();
	public static Item AIR = new ItemAir();
	public static Item KEY = new ItemKey();
	public static Item HEART = new ItemHeart();
	public static Item HALF_HEART = new ItemHalfHeart();
	
	public abstract int getId();
	public abstract String getName();
	public abstract float[] getTextureCoordinates();
	
	static{
		itemMap.put(256, AIR);
		itemMap.put(257, KEY);
		itemMap.put(258, HEART);
		itemMap.put(259, HALF_HEART);
	};
	
	public static Item getItem(int id){
		return itemMap.get(id);
	}
}
