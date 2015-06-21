/**
 * 
 */
package com.dsgn.prac.snkladder;

/**
 * @author sam
 *
 */
public interface IEntity {

	public enum ENTITY_TYPE {
			SNAKE('S'), LADDER('L'), BOLCK('B'), EMPTY ('E'), PLAYER('P');


			char sym;
			
			ENTITY_TYPE (char s) {
				sym = s;
			}
			
			
			public char getSym() {
				return sym;
			}
			
	}
	
	
	
	int getX();
	
	String getType();
	
}
