/**
 * 
 */
package com.dsgn.prac.snkladder;

/**
 * @author sam
 *
 */
public class Ladder extends Entity {
	
  public static int boardSize = 10;
	
  public Ladder (int x, int max) {
	 ent = ENTITY_TYPE.LADDER;
	 this.x = x + ((int) Math.random()) % (max - x);
 }

}