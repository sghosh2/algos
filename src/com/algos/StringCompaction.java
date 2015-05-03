package com.algos;

public class StringCompaction {

	public static void main(String[] args) {
		stringCompaction("aaaabbbbccc$".toCharArray());

	}

	public static void stringCompaction(char[] seq) {

		StringBuffer str = new StringBuffer();

		int len = seq.length;

		int start = 0;
		int end = len - 1;

		while (seq[start] != seq[end]) {

			int changeIndex = findChangeIndex(seq, start, end, seq[start]);
			str.append(seq[start]).append(changeIndex - start +1);
			start = changeIndex+1;
		}

		System.out.println(str);
	}

	private static int findChangeIndex(char[] seq, int start, int end, char symb) {
        
		
		while (start <= end) {
			int mid = start + (end-start)/2;
			
			if(symb == seq[mid]) {
				start = mid+1;
			} else {
				if(symb == seq[mid-1])
					return mid-1;
				end = mid-1;
			}
		}
		
		return 0;
	}
}
