/**
 * 
 */
package com.algos.fk;

/**
 * @author sam
 *
 */
public class SomeProbs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		findCeleb();
	}

	/**
	 * N people are there. knows(A,B) return true if A knows B, else false.
	 * Celebrity: A is called a celebrity If A knows none Everyone knows A 
	 * Get celebrity, with less number of knows() method usage.
	 */
	public static void findCeleb() {
		
		int [] celebs = { 1, 2, 3, 4 , 5, 6};
		int [][] knownMat = {{1, 1, 0 , 1, 1, 0}, 
							 {1, 1, 0 , 0, 1, 0},
							 {1, 1, 1 , 0, 1, 0}, 
							 {0, 0, 0 , 1, 1, 0},
							 {0, 0, 0 , 0, 1, 0}, 
							 {0, 0, 0 , 1, 1, 1}};
		
		int celeb = findCeleb(celebs, knownMat, 0, celebs.length-1);
		
		if(celeb > -1)
			System.out.println("Celebraity is " + celebs[celeb]);
		else 
			System.out.println("no cleb found.");
	}

	private static int findCeleb(int[] celebs, int[][] knownMat, int low, int high) {
		if(low==high)
			return low;
		
		if(low < high) {
			int mid  = low + (high - low)/2;
			int leftCeleb = findCeleb(celebs, knownMat, low, mid);
			int rightCeleb = findCeleb(celebs, knownMat, mid+1, high);
			
			if(leftCeleb == -1 && rightCeleb == -1)
				return -1;
			else if(leftCeleb == -1)
				return rightCeleb;
			else if(rightCeleb == -1)
				return leftCeleb;
			else 
				return findRelation(knownMat, leftCeleb, rightCeleb);
		}
			
		return -1;
	}

	private static int findRelation(int[][] knownMat, int low, int high) {
		boolean lowKnowsHigh = knownMat[low][high] == 1;
		boolean highKnowsLow = knownMat[high][low] == 1;
		
		if((lowKnowsHigh && highKnowsLow) || !(lowKnowsHigh || highKnowsLow)) {
			return -1;
		} else if(lowKnowsHigh && !highKnowsLow)
			return high;
		else
			return low;
	}
}
