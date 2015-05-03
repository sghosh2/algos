/**
 * 
 */
package com.algos.google;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author sam
 *  Given a Binary search tree of integer values. return the count of 
 *  nodes where all the nodes under that sub-tree lies between a given 
 *  range [x, y]. assume there are more than 100,000 nodes
 * 
 */
public class RangeQueryinBST<T> {
	
	public BSTNode<T> root = null;
	
	public static class BSTNode<T> {
		
		public T data;
		public BSTNode<T> left;
		public BSTNode<T> right;
		
		public BSTNode(T t) {
			this.data = t;
		}
	}
	
	public  RangeQueryinBST() {
		root =  new BSTNode<T>(null);
	}
	

}
