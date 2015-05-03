/**
 * 
 */
package com.algos;

/**
 * @author divija
 *
 * Given n dice each with m faces, numbered from 1 to m, find the number of ways to get 
 * sum X. X is the summation of values on each face when all the dice are thrown.
 * 
 */
public class DiceThrowDP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(diceThrow(6, 3, 8));
		
	}
	
	private static int diceThrow(int n, int m, int x) {
		int counter [][] = new int[n][x+1];
		
		int lastupdatedindex = m;
		
		for(int j = 1; j <= m; j++) {
			if(j<=x)
				counter[0][j] = 1;
		}
		
		for (int i = 1; i < n; i++) {
			for(int j = 1; j <= m; j++) {
				int curentNum = j;
				for(int k = 1; k <= lastupdatedindex; k++) {
					if(k + curentNum <= x) {
						if(counter[i][k + curentNum] == 0) {
							counter[i][k + curentNum] = counter[i-1][k + curentNum];
						}
						
					//upadte the reacability    
					//(current number reachability) = (current number reachability) + (current number reachability till last iteration)
					//                                + (previous number reachability)
						// 
						counter[i][k + curentNum] = counter[i][k + curentNum] + counter[i-1][k];
					}
					
					}
				lastupdatedindex = lastupdatedindex + curentNum;

			}
			
		}
		
		return counter[n-1][x];
	}

}
