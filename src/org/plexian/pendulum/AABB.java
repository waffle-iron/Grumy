package org.plexian.pendulum;

/**
 * Provides the required methods and fields to test and run collisions with objects.
 * @author walt
 *
 */
public class AABB{
   public Vector2d pos, size;
   
   /**
    * 
    * @param pos
    * @param size
    */
   public AABB(Vector2d pos, Vector2d size){
      this.pos = pos;
      this.size = size;
   }
   
   /**
    * Check if two AABBs colide.
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
   
   public void move(double x, double y){
	   this.pos.setX(x);
	   this.pos.setY(y);
   }
   
   public void resize(double sizeX, double sizeY){
	   this.size.setX(sizeX);
	   this.size.setY(sizeY);
   }
}