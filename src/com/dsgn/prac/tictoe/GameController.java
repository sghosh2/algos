/**
 * 
 */
package com.dsgn.prac.tictoe;

import com.dsgn.prac.tictoe.Team.Symbol;

/**
 * @author sam
 *
 */
public class GameController {

    public static boolean makeMove(GameState state, int x, int y, String uid) {
		
    	if (state.getActive().isMember(uid)) {
    		if(validateMove(state, x, y)) {
        		state.getState()[x][y] = state.getActive().getSymbol();
        		return true;
    		} else {
    			System.out.println("Not avalid Move");
    			return false;
    		}
    	} else {
    		System.out.println("Not a team member");
    		return false;
    	}
    	
	}
	
	public static boolean validateMove(GameState state, int x, int y) {
		if(x > state.getXmax() || x < 0 || y > state.getYMax() || y < 0)
			return false;
		
		if(!state.getState()[x][y].equals(Symbol.E))
			return false;
		
		return true;
		
	}
	
	public static boolean isWin(GameState state, int x, int y, String uid) {
		
		boolean isWin = true;
		
		for (int i=0; i < state.getXmax(); i++) {
			isWin &= state.getState()[i][y] == state.getActive().getSymbol();
		} 
		if(!isWin) {
			isWin = true;
			for (int i=0; i < state.getYMax(); i++) {
				isWin &= state.getState()[x][i] == state.getActive().getSymbol();
			} 
		}
		if(!isWin) {
			isWin = true;
			int row = state.getYMax()-1;
			for (int i=0; i < state.getYMax(); i++) {
				isWin &= state.getState()[row-i][i] == state.getActive().getSymbol();
			} 
		}
		if(!isWin && x==y) {
			isWin = true;
			for (int i=0; i < state.getYMax(); i++) {
				isWin &= state.getState()[i][i] == state.getActive().getSymbol();
			} 
		}	

		
		return isWin;
	}
	
}
