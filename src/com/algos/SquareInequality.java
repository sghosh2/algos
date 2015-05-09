/**
 * 
 */
package com.algos;

/**
 * 
 * Given a positive number n, count all distinct Non-Negative Integer pairs 
 * (x, y) that satisfy the inequality x*x + y*y < n.
 * 
 * @author sam
 *
 */
public class SquareInequality {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		squIneq(36);
	}

	public static void squIneq(int n) {
		int sqtn = (int)Math.sqrt(n);
		int totalCount = 0;
		int x = 1; 
		
		// this determine that if x*x +x*x < n then x*x +y*y < n for all y < x
		for (; x<=sqtn-1 && 2*x*x < n; x++ ) {
			totalCount +=x;
		}
		
		/// This is now finding x*x +y*y < n where x*x +x*x > n 
		// this is starting from high to low
		int y = 1;
		int maxX = x-1;
		int count = 0;
		for (x = sqtn-1; x > maxX; x--) {
			int diff = n - x*x;
			
			for(; y < x && y*y < diff; y++) {
				count++;
			}
			totalCount +=count;
		}
		System.out.println("total nuber of pairs are " + totalCount);
	}
	
}
