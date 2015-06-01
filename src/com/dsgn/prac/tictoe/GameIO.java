/**
 * 
 */
package com.dsgn.prac.tictoe;

import java.util.Scanner;

import com.dsgn.prac.tictoe.Team.Symbol;

/**
 * @author sam
 *
 */
public class GameIO {

	public static GameOrganizer go;
	
	public void renderState(GameState state) {
		Symbol[][] st = state.getState();
		
		for(int i =0; i < state.getXmax(); i++) {
			
			for(int j =0; j < state.getYMax(); j++) {
				System.out.print(st[i][j].ch + " ");
			}
			System.out.println();
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		go = new GameOrganizer();

		int gameid = go.initiateGame(3, "A", "B", new String[]{"ua"}, new String[]{"ub"});
		
		GameState gs = go.getGame(gameid);
		GameIO io = new GameIO();
		io.renderState(gs);
		Scanner in = new Scanner(System.in);
		
		int x,y;
        String uid;
		
		for (;;) {
			x = in.nextInt();
			y = in.nextInt();
			uid = in.next();
			if(x == -1 || y == -1) 
				break;
			
			if(GameController.makeMove(gs, x, y, uid)) {
				if (GameController.isWin(gs, x, y, uid)) {
				System.out.println(gs.getActive().getName()+ " has won.");
				io.renderState(gs);
				break;
			}
				gs.toggleActive();
			}
			io.renderState(gs);
			
			
		}
		
		
		
	}

}
