/**
 * 
 */
package com.algos.fk;

/**
 * @author sam
 *
 */
public class KthLargest {

	/**
	 * There are M chocolate packets each packet can have variable number of
	 * chocolates in each packet. There are N students (N < M). Distribute
	 * chocolate packets to student such that 
	 * a) each student gets 1 packet 
	 * b) suppose m1,m2,…mn are the packets which are chosen to be distributed in
	 * sorted order of number of chocolates in them (nm-n1 must be minimum) 
	 * M = 1, 3, 4, 6 (4 packets with specified number of chocolates in them) 
	 * N = 2
	 * Ans = 3,4
	 */
	
	public static void main(String[] args) {
		int [] arr = {1, 3, 4, 6};
		//arr = new int[]{1, 10, 4, 6, 9};
		findKth(arr, 2);
		
	}
	
	
	static void findKth(int [] a, int k) {
		
		int h = a.length-1, l = 0;
		
		int index = partition(a, l, h);
		
		while (index+1 != k) {
			
			if(index+1 < k) {
				index = partition(a, index+1, h);
			} else {
				index = partition(a, l, index-1);
			}
			
		}
		System.out.println(String.format("Max %d, Min %d", a[0], a[index])); 
	}
	
	static int partition(int [] a, int l, int h) {
		
		int index = (int) (Math.random()) % (h-l+1);
		
		int indexElem = a[l+index];
		swap(a, l+index, h);
		
		int i = l;
		int j = h-1;
		while(i<=j) {
			if(a[i]>=indexElem) {
				i++;
			} else {
				swap(a, i, j);
				j--;
			}
		}
		swap(a, i, h);
		return i;
	}

	private static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	


}
