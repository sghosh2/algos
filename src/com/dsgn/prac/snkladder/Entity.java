/**
 * 
 */
package com.dsgn.prac.snkladder;

/**
 * @author sam
 *
 */
public class Entity implements IEntity {

	int x;
	
	ENTITY_TYPE ent;

	
	public Entity() {
		x = 0;
		ent = ENTITY_TYPE.EMPTY;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public ENTITY_TYPE getEnt() {
		return ent;
	}

	public void setEnt(ENTITY_TYPE ent) {
		this.ent = ent;
	}

	@Override
	public String getType() {
		return null;
	}

}