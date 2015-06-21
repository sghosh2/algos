/**
 * 
 */
package com.dsgn.prac.snkladder;

/**
 * @author sam
 *
 */
public class Snake extends Entity {
	
  public Snake (int x) {
	 ent = ENTITY_TYPE.SNAKE;
	 this.x = ((int) Math.random()) % x;
 }

}