package src.com.designModel.proxy.staticproxy;

/*
 * @description 代理模式 之 静态代理 -- StaticProxy代理类
 * @description StaticProxy，在代理模式中，叫做代理类，增强StaticProxyImpl实现类 
 * @description 一个代理类，也实现了接口，是增强版的实现类
 */
public class StaticProxy implements StaticProxyInterface{
	
	private StaticProxyImpl staticProxyImpl;
	public StaticProxy(StaticProxyImpl staticProxyImpl){
		this.staticProxyImpl = staticProxyImpl;
	}
	public void queryInfo(){ //相当于在这里封装了一次
		//事务处理之前，可以执行一段代码
		System.out.println("事务处理之前...");
		//调用委托类的方法
		staticProxyImpl.queryInfo();
        // 事务处理之后，可以执行一段代码
		System.out.println("事务处理之后...");
	}
}
