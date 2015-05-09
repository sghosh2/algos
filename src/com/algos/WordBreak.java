/**
 * 
 */
package com.algos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author sam
 * Given an input string and a dictionary of words, find out if the input string 
 * can be segmented into a space-separated sequence of dictionary words. See 
 * following examples for more details. This is a famous Google interview 
 * question, also being asked by many other companies now a days.
 * Consider the following dictionary
 * { i, like, sam, sung, samsung, mobile, ice, cream, icecream, man, go, mango}
 * Input:  ilike
 * Output: Yes 
 * 
 * The string can be segmented as "i like".
 * 
 * Input:  ilikesamsung
 * Output: Yes
 * The string can be segmented as "i like samsung" or "i like sam sung".
 *
 */
public class WordBreak {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String Dict = "i,like,sam,sung,samsung,mobile,ice,"
				+ "cream,icecream,man,go,mango";
		
		String input = "ilikesamsung";
		
		
		System.out.println( -1 << 29);
		System.out.println( 0 << 29);
		System.out.println( 3 << 3);
		System.out.println( 2 << 29);
		System.out.println( 3 << 29);
		
		AtomicInteger ctl = new AtomicInteger(ctlOf( -1 << 29, 0));
		
		int CAPACITY   = (1 << 29) - 1;
		
		System.out.println(ctl.get());
		System.out.println(ctl.get() & ~CAPACITY);
		ctl.incrementAndGet();
		System.out.println(ctl.get() & CAPACITY);
	} 
	static int ctlOf(int rs, int wc) { return rs | wc; }
/*	private static List<String> StringBreak(String d, String in) {
		
		
		
	}*/

}
