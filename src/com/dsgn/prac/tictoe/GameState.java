/**
 * 
 */
package com.dsgn.prac.tictoe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.dsgn.prac.tictoe.Team.Symbol;

/**
 * @author sam
 *
 */
public class GameState {

	private Symbol[][] state;
	
	private int Xmax, YMax;
	
	private Team t1, t2;
	
	private Team active;
	
	private int gameId;
	
	private Map<String, Team> teams = new HashMap<String, Team>();

	public GameState(Symbol[][] state, int xmax, int yMax, Team t1, Team t2,
			int gameId) {
		super();
		this.state = state;
		for(int i =0; i < xmax; i++)
		 Arrays.fill(this.state[i], Symbol.E);
		Xmax = xmax;
		YMax = yMax;
		this.t1 = t1;
		this.t2 = t2;
		this.active = t1;
		this.gameId = gameId;
		teams = new HashMap<String, Team>();
		teams.put(t1.getName(), t1);
		teams.put(t2.getName(), t2);
	}
	
	
	
	public GameState(Symbol[][] state, int xmax, int yMax, int gameId) {
		super();
		this.state = state;
		for(int i =0; i < xmax; i++)
			 Arrays.fill(this.state[i], Symbol.E);
		Xmax = xmax;
		YMax = yMax;
		this.t1 = t1;
		this.t2 = t2;
		this.active = t1;
		this.gameId = gameId;
	}

	public Symbol[][] getState() {
		return state;
	}

	public void setState(Symbol[][] state) {
		this.state = state;
	}

	public int getXmax() {
		return Xmax;
	}

	public void setXmax(int xmax) {
		Xmax = xmax;
	}

	public int getYMax() {
		return YMax;
	}

	public void setYMax(int yMax) {
		YMax = yMax;
	}

	public Team getT1() {
		return t1;
	}

	public void setT1(Team t1) {
		this.t1 = t1;
	}

	public Team getT2() {
		return t2;
	}

	public void setT2(Team t2) {
		this.t2 = t2;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public Team getActive() {
		return active;
	}

	public void setActive(Team active) {
		this.active = active;
	}
		
	public void toggleActive() {
		this.active = active == t1 ? t2: t1;
	}
	
	public void addplayer(String teamName, String uId) {
		Team t = this.teams.get(teamName);
		if(t!=null)
			t.addPlayers(uId );
	}
	
}
