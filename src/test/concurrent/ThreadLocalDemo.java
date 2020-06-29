package test.concurrent;

import java.io.ObjectInputStream.GetField;
import java.util.Random;

/*
 * @desc Java ThreadLocal test
 * @time 2020-5-12
 * @author zhouaryang
 */
public class ThreadLocalDemo implements Runnable{

	private final static ThreadLocal<People> threadlocal = new ThreadLocal<>();
	
	public static void main(String []args){
		ThreadLocalDemo td = new ThreadLocalDemo();
//		线程共享数据可以使用new Thread(object)来实现，其中object是thread or runnable的实现类。
//		td是共享的，
		Thread t1 = new Thread(td);
		Thread t2 = new Thread(td);
		t1.start();
		t2.start();
		
	}
	@Override
	public void run(){
		accessStudent();
	}
	public void accessStudent(){
		// 获取当前线程的名字
	      String currentThreadName = Thread.currentThread().getName();
	      System.out.println(currentThreadName + " is running!");
	      // 产生一个随机数并打印
	      Random random = new Random();
	      int age = random.nextInt(100);
	      System.out.println("thread " + currentThreadName + " set age to:" + age);
	    //从threadLocal中获取
	      People people = getPeople(); 
	      people.setAge(age);
	      System.out.println("thread " + currentThreadName + " first read age is:"
	            + people.getAge());
	      try {
	         Thread.sleep(500);
	      }
	      catch (InterruptedException ex){
	         ex.printStackTrace();
	      }
	      System.out.println("thread " + currentThreadName + " second read age is:"
	            + people.getAge());
	}
	
	protected People getPeople(){
		People people = threadlocal.get();
		
//		线程首次执行此方法的时候，threadLocal.get()肯定为null
		if(people == null){
			// 创建一个对象，并保存到本地线程变量threadLocal中
			people = new People();
			threadlocal.set(people);
		}
		return people;
//		return peo; // 这个时候可能用同一个peo,数据发生错乱。
	}
//	People peo = new People();
}

 class People{
	private int age = 0;
	
	public int getAge(){
		return age;
	}
	
	public void setAge(int age){
		this.age = age;
	}
}