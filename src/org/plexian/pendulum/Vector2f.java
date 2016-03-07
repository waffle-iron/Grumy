/**
 * Copyright Plexian Studios, 2014-2016. All Rights Reserved.
 * Released under Plexian License (see plexian.org/license).
 * If unable to obtain license, please email support@plexian.org.
 */
package org.plexian.pendulum;

public class Vector2f {
	public static Vector2f ZERO = new Vector2f(0, 0);
	public float x, y;
	
	/**
	 * Creates a Vector2f with x and y set to 0.
	 */
	public Vector2f(){
		this(0, 0);
	}
	
	/**
	 * Creates a Vector2f with the given initial x and y values.
	 * @param x The x value to use as initial value. 
	 * @param y The y value to use as initial value.
	 */
	public Vector2f(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a new Vector2f that contains the passed vector's information.
	 * @param vector2f The vector to copy.
	 */
	public Vector2f(Vector2f vector2f){
		this.x = vector2f.x;
		this.y = vector2f.y;
	}
	
	/**
	 * Sets the value of this.x to x.
	 * @param x The x value to set.
	 */
	public void setX(float x){
		this.x = x;
	}
	
	/**
	 * Fetches the value of this.x.
	 * @return The x value of this vector.
	 */
	public float getX(){
		return x;
	}
	
	/**
	 * Sets the value of this.y to y.
	 * @param y The y value to set.
	 */
	public void setY(float y){
		this.y = y;
	}
	
	/**
	 * Fetches the y value of this vector.
	 * @return The y value of this vector.
	 */
	public float getY(){
		return y;
	}
	
	/**
	 * Add x and y to this.x and this.y.
	 * @param x X value to add.
	 * @param y Y value to add.
	 * @return this
	 */
	public Vector2f add(float x, float y){
		this.x += x;
		this.y += y;
		
		return this;
	}
	
	/**
	 * Add passed vector to this vector.
	 * @param vector2f Vector to add to this vector.
	 * @return this
	 */
	public Vector2f add(Vector2f vector2f){
		this.x += vector2f.x;
		this.y += vector2f.y;
		
		return this;
	}
	
	/**
	 * Subtracts x and y from this.x and this.y.
	 * @param x X value to subtract.
	 * @param y Y value to subtract.
	 * @return this
	 */
	public Vector2f subtract(float x, float y){
		this.x -= x;
		this.y -= y;
		
		return this;
	}
	
	/**
	 * Subtract passed vector from this vector.
	 * @param vector2f Vector to subtract from this vector.
	 * @return this
	 */
	public Vector2f subtract(Vector2f vector2f){
		this.x -= vector2f.x;
		this.y -= vector2f.y;
		
		return this;
	}
	
	/**
	 * Multiply this.x and this.y by x and y. Note that if you want to leave
	 * values as they were, simple use 1, not 0, as the parameter.
	 * @param x The value to multiply X by.
	 * @param y The value to multiply Y by.
	 * @return this
	 */
	public Vector2f multiply(float x, float y){
		this.x *= x;
		this.y *= y;
		
		return this;
	}
	
	/**
	 * Multiply this vector by passed vector.
	 * @param vector2f The vector to multiply this vector by.
	 * @return this
	 */
	public Vector2f multiply(Vector2f vector2f){
		this.x *= vector2f.x;
		this.y *= vector2f.y;
		
		return this;
	}
	
	/**
	 * Divide this.x and this.y by x and y. Note that if you want to leave
	 * values as they were, simple use 1, not 0, as the parameter.
	 * @param x The value to divide X by.
	 * @param y The value to divide Y by.
	 * @return this
	 */
	public Vector2f divide(float x, float y){
		this.x /= x;
		this.y /= y;
		
		return this;
	}
	
	/**
	 * Divide this vector by passed vector.
	 * @param vector2f The vector to divide this vector by.
	 * @return this
	 */
	public Vector2f divide(Vector2f vector2f){
		this.x /= vector2f.x;
		this.y /= vector2f.y;
		
		return this;
	}
	
	/**
	 * Find the distance between two points.
	 * @param vector2f The second vector to calculate distance from.
	 * @return The distance between the two points.
	 */
	public float distance(Vector2f vector2f){
		/**
		 * Basically all we do is this:
		 * 
		 * 	_--------------------------
		 * - (x1 - x2)^2 + (y1 - y2)^2
		 */
		return (float) Math.sqrt(((this.x - vector2f.x) * (this.x - vector2f.x)) + ((this.y - vector2f.y) * (this.y - vector2f.y)));
	}
	
	/**
	 * This allows us to convert the vector to a string.
	 * @return Formatted string containing vector position.
	 */
	@Override
	public String toString(){
		return new String(x + "," + y);
	}
}
