package com.ibm.javanet.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThradMaxFinder {

	public static int max(int[] data) throws InterruptedException, ExecutionException {
		if (data.length == 1) {
			return data[0];
		} else if (data.length == 0) {
			throw new IllegalArgumentException();
		}
		
		//将任务分为两个部分
		FindMaxTask task1 = new FindMaxTask(data, 0, data.length / 2);
		FindMaxTask task2 = new FindMaxTask(data, data.length / 2, data.length);
		
		//创建两个线程
		ExecutorService service = Executors.newFixedThreadPool(2);
		Future<Integer> future1 = service.submit(task1);
		Future<Integer> future2 = service.submit(task2);
		
		return Math.max(future1.get(), future2.get());
	}
	
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		try {
			int max = max(a);
			System.out.println(max);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
