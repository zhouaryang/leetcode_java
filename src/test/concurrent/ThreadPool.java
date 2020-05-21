package test.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * @desc Java four ThreadPool test
 * @time 2020-5-11
 * @author zhouaryang
 */

public class ThreadPool {
	
	/*
	 * @desc:创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
	 * 		线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
	 * @print:
	 */
	public void fixedThreadPool(){
//		创建一个可重用的固定数量的线程池
		ExecutorService pool = Executors.newFixedThreadPool(2);
//		创建线程
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
//		将线程加入池 并执行
		pool.execute(t1);
		pool.execute(t2);
//		t1.start();
//		关闭线程池
		pool.shutdown();
	}
	
	/*
	 * @desc:创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
	 */
	public void scheduledThreadPool(){
		ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);
		executorService.scheduleAtFixedRate(new Runnable() {//每隔一段时间出发异常
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("====================="+Thread.currentThread().getName());
			}
		}, 1000, 5000, TimeUnit.MILLISECONDS);
		
		executorService.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(System.nanoTime()+" "+Thread.currentThread().getName());
			}
		}, 1000, 2000, TimeUnit.MILLISECONDS);
	}
	/*
	 * @desc:创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲（60秒不执行任务）的线程，
	 *	当任务数增加时，此线程池又可以智能的添加新线程来处理任务。此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
	 */
	public void cachedThreadPool(){
		ExecutorService pool = Executors.newCachedThreadPool();
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
//		用这样的方法会乱序，如果想让有序可以用容器，list，queue尝试添加t
		
	/*	pool.execute(t1); //池中只有一个线程，
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);*/
		
		List<Thread> list = new LinkedList<>();
		list.add(t1);list.add(t2);list.add(t3);list.add(t4);
		for(Thread t:list){
			pool.execute(t);
		}
		pool.shutdown();
	}
	
	/*
	 * @desc:创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。
	 * 		如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
	 */
	public void singleThreadExecutor(){
		ExecutorService pool = Executors.newSingleThreadExecutor();
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		pool.execute(t1); //池中只有一个线程，
		pool.execute(t2);
		pool.shutdown();
	}
}

class MyThread extends Thread{
//	@Override 写不写区别不大，有一点：写上之后，编译器可以认为是要覆盖，制类型public,protected，返回值，参数列表类型要一致，否则报错，不写则认为是定义的新方法。
	@Override
	public void run(){
		System.out.println(Thread.currentThread().getName()+"正在执行。。。");
	}
}
