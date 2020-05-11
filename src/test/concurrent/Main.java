package test.concurrent;

import javax.swing.GroupLayout.ParallelGroup;

public class Main {
	public static  ThreadPool pool = new ThreadPool();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		pool.fixedThreadPool();
//		pool.singleThreadExecutor();
//		pool.cachedThreadPool();
		pool.scheduledThreadPool();
		
	}
	
	
	
}
