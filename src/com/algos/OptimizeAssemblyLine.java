/**
 * 
 */
package com.algos;

/**
 * @author sam
 * 
 * Two assembly lines, 1 and 2, each with stations from 1 to n.
 * 
 * A car chassis must pass through all stations from 1 to n in order
 * (in any of the two assembly lines). i.e. it cannot jump from station i to 
 * station j if they are not at one move distance.
 * 
 * The car chassis can move one station forward in the same line, or one station 
 * diagonally in the other line. It incurs an extra cost t[i, j] to move to 
 * station j from line i. No cost is incurred for movement in same line.
 * 
 * The time taken in station j on line i is a[i, j].
 *  
 * S [i, j[ represents a station j on line i.
 * 
 * 
 * 
 * Now minimize the time
 */
public class OptimizeAssemblyLine {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int stations [] [] = {{2, 4, 5, 2, 7}, {8, 1, 4, 3, 2}};
		int transitions [] [] = {{1, 2, 1, 3}, {1, 2, 2, 1}};
		
		stations = new int[][]{{4, 5, 3, 2}, {2, 10, 1, 4}};
		transitions = new int[][]{{7, 4, 5}, {9, 2, 8}};
		
		int e[] = {10, 12}, x[] = {18, 7};
	
		System.out.println(optimizeTime(stations, transitions, e, x ));
		
	}
	
	private static int optimizeTime(int s[][], int t [][], int [] e, int [] x) {
		
		int minTime[][] = new int[2][s[0].length];
		
		minTime[0][0] = s[0][0] + e[0];
		minTime[1][0] = s[1][0] + e[1];
		
		
		for(int i = 1; i< s[0].length; i++) {
			minTime[0][i] = min(minTime[0][i-1],
								minTime[1][i-1] + t[1][i-1]) + s[0][i];
			
			minTime[1][i] = min(minTime[1][i-1],
					minTime[0][i-1] + t[0][i-1]) + s[1][i];
		}
		
		return min(minTime[0][s[0].length-1] + x[0], minTime[1][s[0].length-1] + x[1] );
	}
	
	
	private static int min (int l, int r) {
		return l <= r ? l : r;
	}
}
