/**
 * Copyright 2016 The Plexian Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.plexian.grumy.math;

public class Vector3f {
    public static Vector3f ZERO = new Vector3f(0, 0, 0);
    public float x, y, z;

    /**
     * Creates a Vector3f with x and y set to 0.
     */
    public Vector3f() {
        this(0, 0, 0);
    }

    /**
     * Creates a Vector3f with the given initial x and y values.
     * 
     * @param x
     *            The x value to use as initial value.
     * @param y
     *            The y value to use as initial value.
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new Vector3f that contains the passed vector's information.
     * 
     * @param vector3f
     *            The vector to copy.
     */
    public Vector3f(Vector3f vector3f) {
        this.x = vector3f.x;
        this.y = vector3f.y;
        this.z = vector3f.z;
    }

    /**
     * Sets the value of this.x to x.
     * 
     * @param x
     *            The x value to set.
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Fetches the value of this.x.
     * 
     * @return The x value of this vector.
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the value of this.y to y.
     * 
     * @param y
     *            The y value to set.
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Fetches the y value of this vector.
     * 
     * @return The y value of this vector.
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the value of this.z to z.
     * 
     * @param z
     *            The z value to set.
     */
    public void setZ(float z) {
        this.z = z;
    }

    /**
     * Fetches the z value of this vector.
     * 
     * @return The z value of this vector.
     */
    public float getZ() {
        return z;
    }

    /**
     * Add x and y to this.x and this.y.
     * 
     * @param x
     *            X value to add.
     * @param y
     *            Y value to add.
     * @param z
     *            Z value to add.
     * @return this
     */
    public Vector3f add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    /**
     * Add passed vector to this vector.
     * 
     * @param vector3f
     *            Vector to add to this vector.
     * @return this
     */
    public Vector3f add(Vector3f vector3f) {
        this.x += vector3f.x;
        this.y += vector3f.y;
        this.z += vector3f.z;

        return this;
    }

    /**
     * Subtracts x and y from this.x and this.y.
     * 
     * @param x
     *            X value to subtract.
     * @param y
     *            Y value to subtract.
     * @param z
     *            Z value to subtract.
     * @return this
     */
    public Vector3f subtract(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    /**
     * Subtract passed vector from this vector.
     * 
     * @param vector3f
     *            Vector to subtract from this vector.
     * @return this
     */
    public Vector3f subtract(Vector3f vector3f) {
        this.x -= vector3f.x;
        this.y -= vector3f.y;
        this.z -= vector3f.z;

        return this;
    }

    /**
     * Multiply this.x, this.y, and this.z by x, y, and z. Note that if you want
     * to leave values as they were, simple use 1, not 0, as the parameter.
     * 
     * @param x
     *            The value to multiply X by.
     * @param y
     *            The value to multiply Y by.
     * @param z
     *            The value to multiply Z by.
     * @return this
     */
    public Vector3f multiply(float x, float y, float z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }

    /**
     * Multiply this vector by passed vector.
     * 
     * @param vector3f
     *            The vector to multiply this vector by.
     * @return this
     */
    public Vector3f multiply(Vector3f vector3f) {
        this.x *= vector3f.x;
        this.y *= vector3f.y;
        this.z *= vector3f.z;

        return this;
    }

    /**
     * Divide this.x, this.y, and this.z by x, y, and z. Note that if you want
     * to leave values as they were, simple use 1, not 0, as the parameter.
     * 
     * @param x
     *            The value to divide X by.
     * @param y
     *            The value to divide Y by.
     * @param z
     *            The value to divide Z by.
     * @return this
     */
    public Vector3f divide(float x, float y, float z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;

        return this;
    }

    /**
     * Divide this vector by passed vector.
     * 
     * @param vector3f
     *            The vector to divide this vector by.
     * @return this
     */
    public Vector3f divide(Vector3f vector3f) {
        this.x /= vector3f.x;
        this.y /= vector3f.y;
        this.z /= vector3f.z;

        return this;
    }

    /**
     * Find the distance between two points.
     * 
     * @param vector3f
     *            The second vector to calculate distance from.
     * @return The distance between the two points.
     */
    /*
     * public float distance(Vector3f vector3f){ /** Basically all we do is
     * this:
     * 
     * _-------------------------- - (x1 - x2)^2 + (y1 - y2)^2
     *
     * return (float) Math.sqrt(((this.x - vector3f.x) * (this.x - vector3f.x))
     * + ((this.y - vector3f.y) * (this.y - vector3f.y))); }
     */
}
