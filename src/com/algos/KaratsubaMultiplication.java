/**
 * 
 */
package com.algos;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * To multiply two n-digit numbers x and y represented in base W, where n is even and equal to 2m (if n is odd instead, 
 * or the numbers are not of the same length, this can be corrected by adding zeros at the left end of x and/or y), 
 * we can write

    x = x1 Wm + x2
    y = y1 Wm + y2

    with m-digit numbers x1, x2, y1 and y2. The product is given by

    xy = x1y1 W2m + (x1y2 + x2y1) Wm + x2y2

    so we need to quickly determine the numbers x1y1, x1y2 + x2y1 and x2y2. The heart of Karatsuba's method lies in the observation 
    that this can be done with only three rather than four multiplications:

    compute x1y1, call the result A
    compute x2y2, call the result B
    compute (x1 + x2)(y1 + y2), call the result C
    compute C - A - B; this number is equal to x1y2 + x2y1.

 * 
 * @author sam
 *
 */
public class KaratsubaMultiplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        long start, stop, elapsed;
        Random random = new Random();
        int N = 1<<15;
        BigInteger a = new BigInteger(N, random);
        BigInteger b = new BigInteger(N, random);

        start = System.currentTimeMillis(); 
        BigInteger c = karatsuba(a, b);
        stop = System.currentTimeMillis();
        System.out.println(stop - start);
        

        start = System.currentTimeMillis(); 
        BigInteger d = a.multiply(b);
        stop = System.currentTimeMillis();
        System.out.println(stop - start);
        
        System.out.println((c.equals(d)));

	}
	
    public static BigInteger karatsuba(BigInteger x, BigInteger y) {

        // cutoff to brute force
        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= 2000) return x.multiply(y);                // optimize this parameter

        // number of bits divided by 2, rounded up
        N = (N / 2) + (N % 2);

        // x = a + 2^N b,   y = c + 2^N d
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));

        // compute sub-expressions
        BigInteger ac    = karatsuba(a, c);
        BigInteger bd    = karatsuba(b, d);
        BigInteger abcd  = karatsuba(a.add(b), c.add(d));

        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2*N));
    }

}
