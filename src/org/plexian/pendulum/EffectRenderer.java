package org.plexian.pendulum;

import java.util.Random;

public class EffectRenderer {
	
	public enum EffectType{
		NONE, EXPLOSION, FIZZLE;
	}
	
	
	public void renderEffect(EffectType type, double x, double y){
		ParticleFactory factory = new ParticleFactory(new Vector2d(x,y));
		
		if(type == EffectType.EXPLOSION){
			factory.render();
		}
	}
}
