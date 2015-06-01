/**
 * 
 */
package com.algos.fk;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

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
		//findCeleb();
		
		char[] pat = "ABC".toCharArray();
		char[] str = "ABDABBC"
		
		searchMinWindow
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
	
	/**
	 * Input :
	 * 
	 * List of edges are given in the format (source,destination) –> (s1,d1)
	 * (s2,d2)
	 * 
	 * There are some error codes with priority.
	 * 
	 * 1 – Loop
	 * 
	 * 2 – Multiple roots
	 * 
	 * 3 – More than two children
	 * 
	 * Output :
	 * 
	 * Indicate the error (considering the priority) in case of any error and
	 * exit the program.
	 * 
	 * Print the tree structure in the bracket notation in case of no errors.
	 * 
	 * (e.g. (A(B(D)(E))(C(F)(G))) .. Here A is root. B and C are children of A.
	 * D and E are children of B. F and G are children of C.)
	 */
	
	public static void findError(ErrNode<Integer>[] links) {
		Arrays.sort(links);
		Map<Integer, Integer> roots = new HashMap<Integer, Integer>();
		
		for(ErrNode<Integer> node: links) {
			
			Integer dest = roots.get(node.dest);
			
			if(roots.get(node.dest) != null)
				System.err.println(" loop is found");
			
			Integer root = roots.get(node.source);
			
			if(root == null) {
				roots.put(node.source, 1);
			} else {
				roots.put(node.source, 2);
			}
			
		}
	}
	
	private static class ErrNode<E extends Comparable<E>> implements Comparator<ErrNode<E>>{
		E source;
		E dest;
		
		ErrNode(E source, E dest) {
			this.source = source;
			this.dest = dest;
		}

		@Override
		public int compare(ErrNode<E> o1, ErrNode<E> o2) {
			
			return o1.source.compareTo(o2.source) == 0 ? o1.dest.compareTo(o2.dest) : o1.source.compareTo(o2.source);
		}
		
	}
	
	/**
	 * It was long description for a DNA problem. Main DNA sequence(a string) is
	 * given (let say strDNA) and another string to search for(let say strPat).
	 * You have to find the minimum length window in strDNA where strPat is
	 * subsequence.
	 */
	
	public static void searchMinWindow(char[] pat, char[] str) {
		int chr [] = new int[26];
		
		for (char ch: pat) {
			int index = ch - 'A';
			chr [index]++;
		}
		for (int i= 0; i< chr.length; i++) {
			if(chr [i]> 0)
			 chr [i]++;
		}
		
		int patlen = pat.length;
		
		int Mstart =0, Mend =str.length-1;
		int lenFound =0;
		for(int end = 0, start = 0 ; end < str.length; end++) {
			
			if(lenFound < patlen) {
			if(chr[str[end]- 'A'] > 1) {
				chr[str[end]- 'A']--;
				lenFound++;
			}
			continue;
			}
			if(lenFound == patlen) {
				while(chr[str[start] - 'A'] == 0) {
					start++;
				} 
				if(end- start < Mend - Mstart){
					Mstart = start;
					Mend = end;
				}
				chr[str[start]- 'A']++;
				lenFound--;
			}
			
		}
		System.out.println(String.format("Start: %d End:%d", Mstart, Mend));
		
	}
	
	
}
