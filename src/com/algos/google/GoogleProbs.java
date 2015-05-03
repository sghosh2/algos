/**
 * 
 */
package com.algos.google;

/**
 * @author sam
 *
 */
public class GoogleProbs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
/*		int[] marks = {2, 5, 7, 8};
		
		System.out.println("Least rod cutting cost : " + 
		recMinCostRodCut(marks, 0, marks.length, 10) );
		
		System.out.println(printMaxA(11));*/
		
		/**
		 *  6  11 14 23 7
		 *  4  5  10 8  9 
		 *  10 7  9  12 15
		 *  7  9  11 13 16
		 *  
		 */
		
		int islands[][] = {{6,  11, 14, 23, 7}, {4,  5,  10, 8 , 9 }, {10, 7,  9,  12, 15} , {7,  9,  11, 13, 16}};
		findPath(islands);
		
		System.out.println(median(1,2,3, 1, 3));
		System.out.println(median(1,3,2));
		System.out.println(median(2,1,3));
		System.out.println(median(2,3,1));
		System.out.println(median(3,1,2));
		System.out.println(median(3,2,1));
		
		int k =2;
		int [] t= new int[k];
		combination(t,k-1,0);
	}

	public static int qaz(int [] nums) {
		int max = 0;
		int lastMin = nums[nums.length - 1];
		
		for(int i = nums.length - 2; i > -1; i--) {
			
			if(nums[i] < nums[i+1]) {
				max = max + 1;
				lastMin = nums[i];
			}
			
		}
		
		return max;
	}
	
	
	/**
	 * 
	 * Find the longest substring with k unique characters in a 
	 * given string
	 * 
	 * "aabbcc", k = 1
	 * Max substring can be any one from {"aa" , "bb" , "cc"}.
	 * 
	 */
	public static void unqMaxSubstr(char[] str, int k) {
		int [] charArray = new int [26];
		int maxst = 0, maxlen = 0;
		int len = 0, unqChar = 0;
		
		for(int i=0; i < charArray.length; i++) {
			while(unqChar>k) {
				charArray[str[i]-'a']--;
				if(charArray[str[i]-'a'] == 0) {
					unqChar--;
				}
				i++;
				len--;
			}
			
			while(unqChar<=k) {
				if(charArray[str[i]-'a']== 0) {
					unqChar++;
				}
				charArray[str[i]-'a']++;
				
				
			}
		}
		
		
	}
	
	/**
	 * How to print maximum number of A’s using given four keys
	 *  Key 1:  Prints 'A' on screen
		Key 2: (Ctrl-A): Select screen
		Key 3: (Ctrl-C): Copy selection to buffer
		Key 4: (Ctrl-V): Print buffer on screen appending it
                 after what has already been printed.
	 */
	public static int printMaxA(int n) {
		
		int [] max = new int [n < 6 ? 7 : n+1];
		
		for(int i =1; i <7; i++) {
			max[i] = i;
		}
		int ctrlV = 3;
		
		if(n < 7)
			return max[n];
		
		for(int i = 7; i <= n; i++) {
			if(max[i-1] + ctrlV > 2 * max[i-3]) {
				max[i] = max[i-1] + ctrlV;
			} else {
				max[i] = 2 * max[i-3];
				ctrlV = max[i-3];
			}
		}
		return max[n];
	}
	
	
	static int n = 4;
	static int []  a;
	static{
		a = new int[n];
		for(int i=0;i<n; i++) {
			a[i] =i+1;
		}
	}
	
	static void combination (int [] t , int k, int index) {
		if(k<0) {
			for(int x : t) {
				System.out.print( " " + x);
			}
			System.out.println();
		}
		else {
			for(int i=index; i< a.length; i++) {
				t[k] = a[i];
				combination(t,k-1,i+1);
			}
		}
		
	}
	
	/**
	 * You are given a log of wood of length `n’. There are `m’ markings on
	 *  the log. The log must be cut at each of the marking. The cost of 
	 *  cutting is equal to the length of the log that is being cut. 
	 *  Given such a log, determine the least cost of cutting.
	 */
/*   public static int recMinCostRodCut(int [] marks, int start, int end, int rodLen) {
	   
	   int cost = Integer.MAX_VALUE, minCost = Integer.MAX_VALUE;
	   
	   if(end - start == 1) {
		   if(end == marks.length){
			   return 0;
		   } else {
			   return marks[end] - marks[start];
		   }
	   } else if (end == start) {
		   return 0;
	   }
	   
	   for(int i = start; i < end; i++) {
		   for(int mid = i+1; mid < end; mid++) {
			   cost = recMinCostRodCut(marks, i, mid) + recMinCostRodCut(marks, mid, end);
			   if(cost < minCost) {
				   minCost = cost;
			   }
		   }
	   }
	   return minCost;
   }*/
	
	/**
	 * Find median
	 *  System.out.println(median(1,2,3));
		System.out.println(median(1,3,2));
		System.out.println(median(2,1,3));
		System.out.println(median(2,3,1));
		System.out.println(median(3,1,2));
		System.out.println(median(3,2,1));
	 */
	static int median(int a, int b, int c) {
		int maxab;
		int median;
		
		if( a > b) {
			maxab = a;
			median = b;
		} else {
			maxab = b;
			median = a;
		}

		if(maxab < c)
			return maxab;
		else return c > median ? c : median; 

	}
	
	static int median(int a, int b, int c, int min, int max) {
		int median = a^b^c^max^min;
		return median;
	}
	
	/**
	 * 
	 * A 2D array is filled with integer, define a flow from one point 
	 * to its neighbor only if the value of current point is not less than 
	 * its neighbor's value. Consider up edge and left edge as east coast, 
	 * bottom edge and right edge as west coast, find all position that 
	 * it can flow to east coast and west cost both
	 * 
	 * solution only did for one half so do it for the other half and 
	 * add them wherever there is 5 in the result array there is a path
	 * 
	 */
	
	static void findPath(int [][] island) {
		int row = island.length;
		int col = island[0].length;
		
		int result1 [][] = new int[row][col];
		int result [][] = new int[row][col];
		
		for(int i =0 ; i < row; i++) {
			result1 [i][0] = 2;
			result [i][col-1] = 3;
		}
		for(int i =0 ; i < col; i++) {
			result1 [0][i] = 2;
			result [row-1][i] = 3;
		}
		
		// find the path reachable to left/upper edge
		for (int i = 0; i<row;i++) {
			for(int j = 0; j < col; j++){
				if(result1[i][j] == 2) {
					if( i+1 < row && island[i][j] <= island[i+1][j]) {
						result1[i+1][j] = 2;
					}
					if( j+1 < col && island[i][j] <= island[i][j+1]) {
						result1[i][j+1] = 2;
					}
				}

			}
		}
		
		System.out.println("Island");
		for (int i = 0; i<row;i++) {
			for(int j = 0; j < col; j++){
				System.out.print( " " + island[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("paths");
		for (int i = 0; i<row;i++) {
			for(int j = 0; j < col; j++){
				System.out.print( " " + result1[i][j]);
			}
			System.out.println();
		}
		
	}
	
	
}