package test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.omg.PortableInterceptor.USER_EXCEPTION;

/*
 * 在这里测试共享数据
 * 1个普通的http请求，会经过4层service,每层service都需要拿到当前请求的用户信息User
 * 		  thread         user        user        user       
 * request  ->  service1 -> service2 -> service3 -> service4 -> return 
 * 
 */
public class ThreadLocalTest {
	
	StringBuilder user = new StringBuilder(); //StringBUffer是多线程安全的
	
	public static void test(){
//		在这里起三个线程分别跑一下试试
//		使用线程池管理
		ExecutorService pool = Executors.newFixedThreadPool(3);
//		ThreadService t1 = new ThreadService();
//		ThreadService t2 = new ThreadService();
		ThreadService service = new ThreadService();
		Thread t1 = new Thread(service);
		Thread t2 = new Thread(service);
		pool.execute(t1);
		pool.execute(t2);
		pool.shutdown();
	}
	
	public  void service1(){
		user.append("service1;");
	}
	
	public  void service2(){
		user.append("service2;");	
	}
	
	public  void service3(){
		user.append("service3;");
	}
	
	public  void service4(){
		user.append("service4;");
	}
	

}
class ThreadService extends ThreadLocalTest implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		service1();
		service2();
		service3();
		service4();
//		此时这个变量user是线程私有的，大家各改各的。
//		如果改成static,全局唯一，则操作同一个变量
		System.out.println(user);
	}
	
}
