/**
 * 
 */
package com.algos.dp;

/**
 * @author sghosh
 *
 */
public class ZigZag {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int longestZigZag(int[] sequence) {
		
		int [][] maxlen = new int[sequence.length][sequence.length];
		
		int len = 0;
		
		for (int i = 0; i < maxlen.length; i++) {
			maxlen[i][0] = 0;
		}
		
		for(int i = 0; i < maxlen.length; i++) {
			for(int j = i+1; j < maxlen.length; j++) {
				
			}
		}
		
		return len;
		
	}
}