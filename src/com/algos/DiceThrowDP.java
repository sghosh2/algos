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
		int counter [] = new int[x+1];
		
		for (int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				int curentNum = j;
				for(int k = 0; k < x - i; k++) {
					counter [k + curentNum] = counter[k] + counter [k + curentNum];
				}
			}
			
		}
		
		return counter[x];
	}

}
