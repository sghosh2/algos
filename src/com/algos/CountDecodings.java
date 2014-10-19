package com.algos;

public class CountDecodings {

/**
 * 	Let 1 represent ‘A’, 2 represents ‘B’, etc. Given a digit sequence, 
 * count the number of possible decodings of the given digit sequence.

	Examples:

	Input:  digits[] = "121"
	Output: 3
	// The possible decodings are "ABA", "AU", "LA"

	Input: digits[] = "1234"
	Output: 3
	// The possible decodings are "ABCD", "LCD", "AWD"

	An empty digit sequence is considered to have one decoding. It may be 
	assumed that the input contains valid digits from 0 to 9 and there are no 
	leading 0′s, no extra trailing 0′s and no two or more consecutive 0′s.
*/
	public static void main(String[] args) {
		
		int digits [] = {1,2,1};
		 digits = new int[]{1,2,3,4};
		 digits = new int[]{1,2,2,2};// ABBB, AVB, ABV, LBB, LV
		
		System.out.println(countDecodings(digits));
	}
	
	private static int countDecodings(int digits []) {
		int decodings [] = new int[digits.length];
		
		decodings [0] = 1;
		
		for(int i=1; i<digits.length; i++) {
			if(digits[i-1] > 2 || (digits[i-1] == 2 && digits[i] > 6)) {
				decodings [i] = decodings [i-1];
			} else {
				decodings [i] = decodings [i-1] + ((i-2 > -1) ? decodings [i-2] : 1);
			}
			
		}
		
		return decodings[digits.length -1];
	}

}
