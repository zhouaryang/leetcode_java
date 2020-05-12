package test.concurrent;

public class Main {
	public static  ThreadPool pool = new ThreadPool();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		pool.fixedThreadPool();
//		pool.singleThreadExecutor();
//		pool.cachedThreadPool();
//		pool.scheduledThreadPool();
		testThreadLocal();
	}
	ThreadLocalTest aLocalTest = new ThreadLocalTest();
	
	
	public static void testThreadLocal(){
		
//		main中调用非静态方法，先new一个对象即可
		Main m = new Main();
		m.aLocalTest.test();
		System.out.println(m.aLocalTest.user);
	}
	
}
