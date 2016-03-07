/**
 * Copyright Plexian Studios, 2014-2016. All Rights Reserved.
 * Released under Plexian License (see plexian.org/license).
 * If unable to obtain license, please email support@plexian.org.
 */
package org.plexian.pendulum;

public class Vector2d {
	public static Vector2d ZERO = new Vector2d(0, 0);
	public double x, y;
	
	/**
	 * Creates a Vector2d with x and y set to 0.
	 */
	public Vector2d(){
		this(0, 0);
	}
	
	/**
	 * Creates a Vector2d with the given initial x and y values.
	 * @param x The x value to use as initial value. 
	 * @param y The y value to use as initial value.
	 */
	public Vector2d(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a new Vector2d that contains the passed vector's information.
	 * @param Vector2d The vector to copy.
	 */
	public Vector2d(Vector2d Vector2d){
		this.x = Vector2d.x;
		this.y = Vector2d.y;
	}
	
	/**
	 * Sets the value of this.x to x.
	 * @param x The x value to set.
	 */
	public void setX(double x){
		this.x = x;
	}
	
	/**
	 * Fetches the value of this.x.
	 * @return The x value of this vector.
	 */
	public double getX(){
		return x;
	}
	
	/**
	 * Sets the value of this.y to y.
	 * @param y The y value to set.
	 */
	public void setY(double y){
		this.y = y;
	}
	
	/**
	 * Fetches the y value of this vector.
	 * @return The y value of this vector.
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * Add x and y to this.x and this.y.
	 * @param x X value to add.
	 * @param y Y value to add.
	 * @return this
	 */
	public Vector2d add(double x, double y){
		this.x += x;
		this.y += y;
		
		return this;
	}
	
	/**
	 * Add passed vector to this vector.
	 * @param Vector2d Vector to add to this vector.
	 * @return this
	 */
	public Vector2d add(Vector2d Vector2d){
		this.x += Vector2d.x;
		this.y += Vector2d.y;
		
		return this;
	}
	
	/**
	 * Subtracts x and y from this.x and this.y.
	 * @param x X value to subtract.
	 * @param y Y value to subtract.
	 * @return this
	 */
	public Vector2d subtract(double x, double y){
		this.x -= x;
		this.y -= y;
		
		return this;
	}
	
	/**
	 * Subtract passed vector from this vector.
	 * @param Vector2d Vector to subtract from this vector.
	 * @return this
	 */
	public Vector2d subtract(Vector2d Vector2d){
		this.x -= Vector2d.x;
		this.y -= Vector2d.y;
		
		return this;
	}
	
	/**
	 * Multiply this.x and this.y by x and y. Note that if you want to leave
	 * values as they were, simple use 1, not 0, as the parameter.
	 * @param x The value to multiply X by.
	 * @param y The value to multiply Y by.
	 * @return this
	 */
	public Vector2d multiply(double x, double y){
		this.x *= x;
		this.y *= y;
		
		return this;
	}
	
	/**
	 * Multiply this vector by passed vector.
	 * @param Vector2d The vector to multiply this vector by.
	 * @return this
	 */
	public Vector2d multiply(Vector2d Vector2d){
		this.x *= Vector2d.x;
		this.y *= Vector2d.y;
		
		return this;
	}
	
	/**
	 * Divide this.x and this.y by x and y. Note that if you want to leave
	 * values as they were, simple use 1, not 0, as the parameter.
	 * @param x The value to divide X by.
	 * @param y The value to divide Y by.
	 * @return this
	 */
	public Vector2d divide(double x, double y){
		this.x /= x;
		this.y /= y;
		
		return this;
	}
	
	/**
	 * Divide this vector by passed vector.
	 * @param Vector2d The vector to divide this vector by.
	 * @return this
	 */
	public Vector2d divide(Vector2d Vector2d){
		this.x /= Vector2d.x;
		this.y /= Vector2d.y;
		
		return this;
	}
	
	/**
	 * Find the distance between two points.
	 * @param Vector2d The second vector to calculate distance from.
	 * @return The distance between the two points.
	 */
	public double distance(Vector2d Vector2d){
		/**
		 * Basically all we do is this:
		 * 
		 * 	_--------------------------
		 * - (x1 - x2)^2 + (y1 - y2)^2
		 */
		return Math.sqrt(((this.x - Vector2d.x) * (this.x - Vector2d.x)) + ((this.y - Vector2d.y) * (this.y - Vector2d.y)));
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
