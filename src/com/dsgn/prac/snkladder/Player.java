package com.dsgn.prac.snkladder;

public class Player extends Entity {
	
	private int id;
	
	private int color;

	public Player(int id, int color, int x) {
		this.ent = ENTITY_TYPE.PLAYER;
		this.id = id;
		this.color = color;
		this.x = x;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public String getType() {
		return null;
	}

}
