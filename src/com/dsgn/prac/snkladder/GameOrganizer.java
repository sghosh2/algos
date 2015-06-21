/**
 * 
 */
package com.dsgn.prac.snkladder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.dsgn.prac.snkladder.GameState;

/**
 * @author sam
 *
 */
public class GameOrganizer {
	private Map<Integer, GameState> activeGames = 
			new ConcurrentHashMap<Integer, GameState>();
	
	private static AtomicInteger gameIdGen = new AtomicInteger(0);
	
	public int initGame(List<Player> players, int max) {
		
		int id = gameIdGen.incrementAndGet();
		
		GameState gs = new GameState(players, id, max);
		
		this.activeGames.put(id, gs);
		
		return id;
		
	}
	
	public GameState getGame(int id) {
		
		GameState gs = this.activeGames.get(id);
		
		if(gs == null) {
			System.out.println("Game is not found for id " + id);
		}
		
		return gs;
		
	}
	
	
}
