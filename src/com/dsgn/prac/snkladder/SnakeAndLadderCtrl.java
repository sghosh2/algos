/**
 * 
 */
package com.dsgn.prac.snkladder;

import com.dsgn.prac.snkladder.IEntity.ENTITY_TYPE;

/**
 * @author sam
 *
 */
public class SnakeAndLadderCtrl {
 
	private GameOrganizer go;
	
	public SnakeAndLadderCtrl (GameOrganizer go) {
		this.go = go;
	}
	
	public void playTurn(int gameId, int usrId) {
		GameState gs = go.getGame(gameId);
		
		if(gs==null) {
			System.out.println("Not a valid Game");
			return;
		}
		
		if(usrId != gs.getActPlayer()) {
			System.out.println("Not an active valid player");
			return;
		}
			
		int val = (int) (Math.random()*10) % 6 +1;
		Player p = gs.getPlayersMap().get(usrId);
		int actPlayer = (gs.getActPlayer()+1) % gs.getPlayersLst().size();
		
		if(p.x + val == gs.getMax()-1) {
			System.out.println(p.getColor() + " has won");
			return;
		} else if(p.x + val < gs.getMax()-1){
			
			if(p.x > 0 || (p.x==0 && val==6)) {
				p.x = p.x + val;
				while(gs.getBoard()[p.x].ent != ENTITY_TYPE.EMPTY) {
					p.x = gs.getBoard()[p.x].x;
				}
			} 
		}
		
		gs.setActPlayer(actPlayer);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
