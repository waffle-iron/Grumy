package org.plexian.pendulum;

import org.plexian.pendulum.EffectRenderer.EffectType;

public class PlayerEffect {
	private Player player;
	private PlayerEffectType effectType;
	
	public enum PlayerEffectType{
		NONE("none"),
		DIE("die"),
		HURT("hurt");
		
		private String name;
		private Player p;
		
		PlayerEffectType(String name){
			this.name = name;
		}
	}
	
	public PlayerEffect(Player player){
		this.player = player;
	}
	
	public void render(){
	//	EffectRenderer.renderEffect(EffectType.EXPLOSION, this.player.getLocation().getX(), this.player.getLocation().getY());
	}
	
	public void setEffectType(PlayerEffectType type){
		this.effectType = type;
	}
}
