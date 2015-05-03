/**
 * 
 */
package com.algos.dp;

/**
 * @author sam
 *
 */
public class CoinChange {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] coins =  {1,2,3};
		System.out.println(" coin chnage for 4 " + coinChange(coins, 4));
		coins = new int []{2, 5, 3, 6};
		System.out.println(" coin change for 10 " + coinChange(coins, 10));

	}
	
	
	public static int coinChange (int coins [], int target) {
		int max[] = new int[target+1];
		
		for (int i= 0; i< coins.length; i++) {
			if(coins[i] <= target) {
				max[coins[i]] = max[coins[i]]+1;
				for(int j = 1; j<= target - coins[i]; j++) {
					if(max[j] > 0) {
						max[j + coins[i]] += max[j];
					}
				}
			}
		}
		return max[target];
	}

}
