package test.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @desc Java Synchoronized test
 * @time 2020-5-13
 * @author zhouaryang
 */
public class SyncTest {

	static int count = 0;
//	共享资源访问
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//测试非互斥
//		NonSync();
		//测试互斥
		Sync();
		
	}
	
	
	public static void NonSync(){
		Thread st = new Thread(){
			@Override
			public void run(){
				for(int i=0; i<10000; ++i) {
					++count;
				}
			}
		};
		
		st.start();
		
		for(int i=0; i<10000; ++i) {
			++count;
		}
		try {
			st.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count);
	}
	
	
	public static void Sync(){
		
		final Object obj = new Object();
		Thread st = new Thread(){
			@Override
			public void run(){
				for(int i=0; i<10000; ++i) {
//					synchronized代码块需要一个参数
					synchronized (obj) {
						++count;
					}
				}
			}
		};
		
		st.start();
		
		for(int i=0; i<10000; ++i) {
			synchronized (obj) {
				++count;
			}
		}
		try {
			st.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count);
	}

}
