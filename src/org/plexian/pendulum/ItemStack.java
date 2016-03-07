package org.plexian.pendulum;

public class ItemStack {
	private int ammount;
	private Material material;
	private boolean canDrop;
	
	public ItemStack(Material m){
		this(m, 1);
	}
	
	public ItemStack(Material m, int ammount){
		this(m, ammount, true);
	}
	
	public ItemStack(Material m, int ammount, boolean canDrop){
		this.material = m;
		this.ammount = ammount;
		this.canDrop = canDrop;
	}
	
	public int getAmmount(){
		return ammount;
	}
	
	public Material getMaterial(){
		return this.material;
	}
	
	public boolean canDrop(){
		return canDrop;
	}
	
	public void setAmmount(int ammount){
		this.ammount = ammount;
	}
	
	public void setMaterial(Material m){
		this.material = m;
	}
	
	public void setCanDrop(boolean canDrop){
		this.canDrop = canDrop;
	}
}
