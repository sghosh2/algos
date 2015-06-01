/**
 * 
 */
package com.dsgn.prac.tictoe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.dsgn.prac.tictoe.Team.Symbol;

/**
 * @author sam
 *
 */
public class GameOrganizer {

	private Map<Integer, GameState> activeGames = 
			new HashMap<Integer, GameState>();
	
	private static AtomicInteger gameIdGen = new AtomicInteger(0);
	
	public int initiateGame(int size, String teamNameA, String teamNameB,String[] ida, String[] idb) {
		int gameId = gameIdGen.incrementAndGet();
		Team teamA = new Team(teamNameA, Symbol.X);
		teamA.setIds(ida);
		Team teamB = new Team(teamNameB,  Symbol.O);
		teamB.setIds(idb);
		
		GameState state = new GameState(new Symbol[size][size], size, size, teamA, teamB, gameId);
		activeGames.put(gameId, state);
		return gameId;
		
	}

	public GameState getGame(int gameId) {
		return activeGames.get(gameId);
	}
	
	public boolean joinGame(int gameId, String userId, String teamName) {
		GameState state = activeGames.get(gameId);
		
		if(state == null) {
			System.out.println("No active games");
			return false;
		} 
		
		state.addplayer(teamName, userId);
		return true;
	}
	
}
