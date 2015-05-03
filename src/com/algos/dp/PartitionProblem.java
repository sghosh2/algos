/**
 * 
 */
package com.algos.dp;

/**
 * @author sam
 *
 */
public class PartitionProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int arr[] = {1, 5, 11, 5};
		arr = new int[] {1, 5, 3};
		partitiononEqual(arr);

	}
	
	public static void partitiononEqual(int arr[]) {
		
		int sumArr [] = new int [arr.length + 1];
		int indexArr [] = new int [arr.length + 1];
		
		sumArr [0] = 0;
		int sum =0;
		int indexEqualindex = -1;
		
		for(int i=0;i<arr.length; i++ ) {
			sum+=arr[i];
			sumArr [i+1] = -1;
		}
		
		sum=sum/2;
		
		for(int i=1; i<=arr.length; i++) {
			
			for(int j = 0; j < i; j++) {
				int currentSum  = sumArr[j] + arr[i-1];
				if(currentSum <= sum && currentSum > sumArr[i]) {
					sumArr[i] = currentSum;
					indexArr [i] = j;
					if(currentSum == sum) {
						indexEqualindex = i;
					}
				}
			}
		}
		
		if(indexEqualindex > -1){
			System.out.println("partion possible");
			StringBuffer str = new StringBuffer();
			for (int i = indexEqualindex; i>0; i = indexArr[i]) {
				str.append(arr[i-1] + ", ");
			}
			System.out.println(str);
		} else 
			System.out.println("partion not possible");
	}
}
