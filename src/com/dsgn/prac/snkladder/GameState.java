/**
 * 
 */
package com.dsgn.prac.snkladder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author sam
 *
 */
public class GameState {

	private Entity board[];
	
	private int max;
	
	private int width;
	
	private int id;
	
	private Map<Integer, Player> playersMap;
	
	private List<Player> playersLst;
	
	private int actPlayer;
	
	//private Player[][] playerBoard;
	
	public GameState(List<Player> players, int id, int x) {
		this.playersLst = players;
		width=x;
		this.max = x*x;
		this.id = id;
		playersMap = new HashMap<Integer, Player>();
		for(Player p: players) {
			playersMap.put(p.getId(), p);
		}
		actPlayer = 0;
		initializeBoard();
	}

	private void initializeBoard() {
		board = new Entity[max];
		Entity empty = new Entity();
		Arrays.fill(board, empty);
		
		int noSnakes = ((int)Math.random()*5);
		
		for(int i = 0; i<noSnakes; i++) {
			int posx;
			do{
			 posx = ((int)Math.random()*max);
			} while (board[posx]!=empty);
			Entity snake = new Snake(posx);
			board[posx] = snake;
			
			do{
				 posx = ((int)Math.random()*max);
				} while (board[posx]!=empty);
			
			Entity ladder = new Ladder(posx, max);
			board[posx]= ladder;
			
		}
		
	}

	public Entity[] getBoard() {
		return board;
	}

	public void setBoard(Entity[] board) {
		this.board = board;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Integer, Player> getPlayersMap() {
		return playersMap;
	}

	public void setPlayersMap(Map<Integer, Player> playersMap) {
		this.playersMap = playersMap;
	}

	public List<Player> getPlayersLst() {
		return playersLst;
	}

	public void setPlayersLst(List<Player> playersLst) {
		this.playersLst = playersLst;
	}

	public int getActPlayer() {
		return actPlayer;
	}

	public void setActPlayer(int actPlayer) {
		this.actPlayer = actPlayer;
	}
	
}
