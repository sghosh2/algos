/**
 * 
 */
package com.algos;

/**
 * @author sam
 *
 */
public class MaxProduct {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(maxProd(7));
		
	}
	
	private static int maxProd(int no) {
		int prod = 0;
		
		int products [][] = new int[no+1][no+1];
		
		products [1][0] = 1;
		products [2][0] = 2;
		products [2][1] = 1;
		
		for (int i = 3; i<=no; i++) {
			
			for(int j = 1; j <=i; j++ ) {
				
				
				
			}
			
		}
		
		return prod;
	}

}
