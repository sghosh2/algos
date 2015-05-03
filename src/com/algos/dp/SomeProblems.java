package com.algos.dp;

public class SomeProblems {

	public static void main(String[] args) {
		
		
		
		isRobotCircular("GLLG".toCharArray());
		
		int [] points = {10, 5, 3};
		waysToScore(points, 20);
		
		int n = 10;
		
		System.out.println(n&(-n));
		int no = -n; 
		StringBuilder str = new StringBuilder();
		while(no != 0) {
			str.append(no & 1);
			no = no >>> 1;
		}
		
		System.out.println(str.reverse());
		// TODO Auto-generated method stub
       
		int [][] island = { {1,1,1,0,1},
	            			{1,0,0,0,1},
	            			{1,0,0,1,1},
	            			{1,1,1,1,1}};
		int [][] island2 = { {0,1,1,1,1},
    						 {1,0,0,0,1},
    						 {1,0,0,1,1},
    						 {1,1,1,1,1}};
		int [][] island3 = { {1,1,1,1,1},
				 			 {1,0,0,0,1},
				 			 {1,0,0,1,1},
				 			 {1,1,1,1,1}};
        findGameOver(island);
        findGameOver(island2);
        findGameOver(island3);
		
		//int [] a = {1,2,1};
		int [] a = {1,2,3,4};
		System.out.println(countDecodings(a));
		
		//MaxProfit
		int [] s= {1, 7, 8, 5, 6};
		System.out.println("max profit :: " + maxProfit(s));
		

	}

	/**
	 * Count Possible Decodings of a given Digit Sequence
	 * Let 1 represent ‘A’, 2 represents ‘B’, etc. Given a digit sequence, count the number of possible decodings of the given digit sequence.
	 * Examples:
	 * Input:  digits[] = "121"
	 * Output: 3
	 * // The possible decodings are "ABA", "AU", "LA"
	 * Input: digits[] = "1234"
	 * Output: 3
	 * // The possible decodings are "ABCD", "LCD", "AWD"
	 * @param seq
	 * @return
	 */
	
	public static int countDecodings(int [] seq) {
		
		int a[] = new int [seq.length];
		
		for (int i=0; i<seq.length; i++) {
			a[i] = 1;
		}
		
		for(int i=1; i < seq.length; i++) {
			a[i] = a[i-1];
			
			if(a[i-1]*10 + a[i] < 27)
				a[i]+= i > 1 ? a[i-2] : 1;
		}
		
		return a[seq.length -1];
	}
	
	/**
	 * Given a rope of length n meters, cut the rope in different parts of 
	 * integer lengths in a way that maximizes product of lengths of all parts. 
	 * You must make at least one cut. Assume that the length of rope is more 
	 * than 2 meters. 
	 * 
	 * Examples:-
	 * 
	 * Input: n = 5
	 * Output: 6 (Maximum obtainable product is 2*3)
	 * Input: n = 10
	 * Output: 36 (Maximum obtainable product is 3*3*4)
	 * 
	 */
	
	public static int MaxProduct(int n) {
		
		int product = 1;
		
		for(int i = 1; i < n; i++) {
			product = Math.max(i * (n-i) , MaxProduct(n-i)*i);
		}
		return 0;
	}
	
	public static int diceThrow(int n, int m, int x) {
		
		int [][] aux = new int[m][x+1];
		
		for(int j = 1; j <= n; j++) {
			aux[0][j] = 1;
		}
		
		for(int i = 1; i< m; i++) {
			
			for(int j = 1; j <= n; j++) {
				
			}
			
		}
		
		
		return aux[m-1][x];
	}
	
	/**
	 * Design an algo to decide if the GO game is over. i.e.
       Given a boolean matrix, write a code to find if an island of 0's is 
       completely surrounded by 1's.
	 */
	
	static int UP = 1;
	static int UR = UP << 1;
	static int RT = UR << 1;
	static int BR = RT << 1;
	static int BT = BR<<1;
	static int BL = BT<<1;
	static int LT = BL<<1;
	static int UL = LT<<1;
	static int SURROUNDED = UP|UR|RT|BR|BT|BL|LT|UL;
	static int NOT = (SURROUNDED + 1);
	
	public static boolean findGameOver(int [][] g) {
		int c = g[0].length;
		int r = g.length;
		int [][] det = new int[r][c];

		for(int i = 0; i < r; i++) {
			if(g[i][0] == 0)
			    det[i][0] = NOT;
			if(g[i][c-1] == 0)
			    det[i][c-1] = NOT;
		}
			
		for(int j = 0; j < c; j++) {
			if(g[0][j] == 0)
				det[0][j] = NOT;
			if(g[r-1][j] == 0)
				det[r-1][j] = NOT;
		}
		
		for(int i=1; i < r-1; i++) {
			for(int j=1; j< c-1; j++) {
				if(g[i][j] == 0) {
					determineNeighbours(det, g, i, j);
					if(det[i][j] == SURROUNDED) {
						System.out.println("Island found!!!");
						return true;
					} 
				}
			}
		}
		System.out.println("No Island found.");
		return false;
	}
	
	
	private static void determineNeighbours(int[][] det, int[][] g, int r, int c) {
		// percolating boundary value of neighboring 0
		det[r][c] = det[r-1][c]&UP|det[r-1][c+1]&UR|det[r][c+1]&RT|det[r+1][c+1]&BR|det[r+1][c]&BT|det[r+1][c-1]&BL|det[r][c-1]&LT|det[r-1][c-1]&UL;
		// Determining neighboring '1's
		det[r][c] = det[r][c]|(g[r-1][c]*UP|g[r-1][c+1]*UR|g[r][c+1]*RT|g[r+1][c+1]*BR|g[r+1][c]*BT|g[r+1][c-1]*BL|g[r][c-1]*LT|g[r-1][c-1]*UL);
		// Determining any non boundary violations
		det[r][c] = det[r][c]|(det[r-1][c]&NOT|det[r-1][c+1]&NOT|det[r][c+1]&NOT|det[r+1][c+1]&NOT|det[r+1][c]&NOT|det[r+1][c-1]&NOT|det[r][c-1]&NOT|det[r-1][c-1]&NOT);
		
	}
	
	/**
	 * 2D matrix with 0s and 1s. Try to find out how many countries in this matrix?
	 * For example:
	 * [[1,1,1,0]
	 * [1,1,0,0]
	 * [0,0,0,1]]
	 * 
	 * return 3, because one for 1s, one for 0s, and one for the last one. 
	 */

	public static int countInt(int [][] map) {
		int counter = 0;
		
		return counter;
	}
	
	/**
	 * given a vector of integers, v[i] represent the stock price on day i. 
	 * Now you may do at most K transactions. you must sell your stock before 
	 * you buy it again and that means you can NOT have two stocks at the same 
	 * time. write a program to find max profit you can get.
	 */
	
	public static int maxProfit(int s[]) {
		int max[] = new int[s.length];
		
		for(int i = 0; i < s.length; i++) {
			for(int j =0; j < i; j++) {
				if(s[i]-s[j] > 0)
					max[i] = (s[i]-s[j] + max[j])>max[i]?(s[i]-s[j] + max[j]):max[i];
				else
					max[i] = max[j]>max[i]?max[j]:max[i];	
			}
		}
		return max[s.length-1];
	}
	
	/**
	 * Consider a game where a player can score 3 or 5 or 10 points in a move. 
	 * Given a total score n, find number of ways to reach the given score.
	 */
	public static void waysToScore (int [] a, int n) {
		
		int [] c = new int[n+1];
		
		for(int i=0; i < a.length; i++) {
			int no = a[i];
			int index = no;
			c[index]++;
			while(index<n) {
				if(index+no<=n && c[index] > 0) {
					c[index+no] += c[index];
				}
				index++;
			}
		}
		System.out.println("Total number of ways this combination can be done is " + c[n]);
		
	}
	
	/**
	 * Given a sequence of moves for a robot, check if the sequence is circular
	 * or not. A sequence of moves is circular if first and last positions of
	 * robot are same. A move can be on of the following.
	 * 
	 * G - Go one unit 
	 * L - Turn left 
	 * R - Turn right
	 * 
	 * Input: path[] = "GLGLGLG"
	 * Output: Given sequence of moves is circular 
	 * 
	 */

	public static void isRobotCircular(char [] path) {
		
		int x =0 ,y =0;

		int dx = 1, dy = 0;
		
		if(0 == path.length-1 && x==0 && y == 0)
			System.out.println("Path is circular");
		
	    for(int i= 0; i<path.length; i++) {

			if(path[i] == 'L') {
				if(dx != 0 && dy == 0) {
					dy =  dx;
					dx = 0;
				} else {
					dx = -1 * dy;
					dy = 0;
				}
			}else if(path[i] == 'R') {
				if(dx != 0 && dy == 0) {
					dy =  -1 * dx;
					dx = 0;
				} else {
					dx = dy;
					dy = 0;
				}
			} else {
				x += dx;
				y += dy;
			}
			if(i == path.length-1 && x==0 && y == 0) {
				System.out.println("Path is circular");
				return;
			}
	    }
	    System.out.println("Path is not circular");
	}
	
	/**
	 * given a vector of integers, v[i] represent the stock price on day i. 
	 * Now you may do at most K transactions. you must sell your stock before 
	 * you buy it again and that means you can NOT have two stocks at the same 
	 * time. write a program to find max profit you can get.
	 */
	
//	public static int maxProfit(int s[], int k) {
//		int max[][] = new int[s.length][k+1];
//		
//		
//		
//		for(int i = 0; i < s.length; i++) {
//			for(int j =0; j < i; j++) {
//				if(s[i]-s[j] > 0)
//					max[i] = (s[i]-s[j] + max[j])>max[i]?(s[i]-s[j] + max[j]):max[i];
//				else
//					max[i] = max[j]>max[i]?max[j]:max[i];	
//			}
//		}
//		return max[s.length-1];
//	}
	
	/**
	 * Given a set of busy time intervals of two people as in a calendar, 
	 * find the free time intervals of both the people so as to arrange a 
	 * new meeting
		input: increasing sequence of pair of numbers
		per1: (1,5) (10, 14) (19,20) (21,24) (27,30)
		per2: (3,5) (12,15) (18, 21) (23, 24)
		ouput: (6,9) (16,17) (22,22) (25,26)
	 */
	
	
}
