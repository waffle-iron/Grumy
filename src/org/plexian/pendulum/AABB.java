package org.plexian.pendulum;

/**
 * Provides the required methods and fields to test and run collisions with objects.
 * @author walt
 *
 */
public class AABB{
	/**
	 * The position of the AABB.
	 */
   public Vector2d pos;
   
   /**
    * The size of the AABB.
    */
   public Vector2d size;
   
   /**
    * Creates a new instance of AABB.
    * @param pos The initial position of the AABB.
    * @param size The initial size of the AABB.
    */
   public AABB(Vector2d pos, Vector2d size){
      this.pos = pos;
      this.size = size;
   }
   
   /**
    * Check if two AABBs collide.
    * @param a The first AABB.
    * @param b The second AABB.
    * @return True if there is a collision between a & b, false if not
    */
   public static boolean collides(AABB a, AABB b){
      if(Math.abs(a.pos.x - b.pos.x) < a.size.x + b.size.x){
         if(Math.abs(a.pos.y - b.pos.y) < a.size.y + b.size.y){
            return true;
         }
      }
      
      return false;
   }
   
   /**
    * Check if point b is inside AABB a.
    * @param a The AABB object.
    * @param b The point to check.
    * @return True if point b is inside a, false if not.
    */
   public static boolean inside(AABB a, Vector2d b){
      if(Math.abs(a.pos.x - b.x) < a.size.x){
         if(Math.abs(a.pos.y - b.y) < a.size.y){
            return true;
         }
      }
      return false;
   }
   

   /**
    * Move the AABB to a position.
    * @param x The X-coordinate to move to.
    * @param y The Y-coordinate to move to.
    */
   public void move(double x, double y){
	   this.pos.setX(x);
	   this.pos.setY(y);
   }
   
   /**
    * Resize the AABB.
    * @param sizeX The new size of the AABB on the X-Axis.
    * @param sizeY The new size of the AABB on the Y-Axis.
    */
   public void resize(double sizeX, double sizeY){
	   this.size.setX(sizeX);
	   this.size.setY(sizeY);
   }
}