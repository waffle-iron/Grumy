package org.plexian.pendulum;

public class RGBA {
	public static RGBA WHITE = new RGBA(255, 255, 255, 1);
	public static RGBA BLACK = new RGBA(0, 0, 0, 1);
	public static RGBA BLUE = new RGBA(0, 0, 1, 1);

	private float r, g, b, a;
	
	public static RGBA parse(String color){
		String[] split = color.split(",");
		String r = split[0], g = split[1], b = split[2], a = split[3];
		
		return new RGBA(Float.parseFloat(r), Float.parseFloat(g), Float.parseFloat(b), Float.parseFloat(a));
	}
	
	public RGBA(float r, float g, float b, float a){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public float getR(){
		return r;
	}
	
	public float getG(){
		return g;
	}
	
	public float getB(){
		return b;
	}
	
	public float getA(){
		return a;
	}
}
