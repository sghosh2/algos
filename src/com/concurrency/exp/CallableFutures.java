/**
 * 
 */
package com.concurrency.exp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author sam
 *
 */

public class CallableFutures {
  private static final int NTHREDS = 10;

  public static void main(String[] args) {
	 runTwo();
    runOne();
  }

  private static void runTwo() {
		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
	    List<FutureTask<Long>> list = new ArrayList<FutureTask<Long>>();
	    for (int i = 0; i < 20000; i++) {
	      Callable<Long> worker = new MyCallable();
	      FutureTask<Long> futureTask = new FutureTask<>(worker);
	      executor.execute(futureTask);
	      list.add(futureTask);
	    }
	    long sum = 0;
	    System.out.println(list.size());
	    // now retrieve the result
	    for (Future<Long> future : list) {
	      try {
	        sum += future.get();
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      } catch (ExecutionException e) {
	        e.printStackTrace();
	      }
	    }
	    System.out.println(sum);
	    executor.shutdown();
	}

private static void runOne() {
	ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
    List<Future<Long>> list = new ArrayList<Future<Long>>();
    for (int i = 0; i < 20000; i++) {
      Callable<Long> worker = new MyCallable();
      Future<Long> submit = executor.submit(worker);
      list.add(submit);
    }
    long sum = 0;
    System.out.println(list.size());
    // now retrieve the result
    for (Future<Long> future : list) {
      try {
        sum += future.get();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    System.out.println(sum);
    executor.shutdown();
}
  


public static class MyCallable implements Callable<Long> {
  @Override
  public Long call() throws Exception {
    long sum = 0;
    for (long i = 0; i <= 100; i++) {
      sum += i;
    }
    return sum;
  }

}
  
}
