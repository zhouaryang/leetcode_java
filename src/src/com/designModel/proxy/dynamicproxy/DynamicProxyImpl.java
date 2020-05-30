package src.com.designModel.proxy.dynamicproxy;

/**
 * @description 代理模式 之 JDK的动态代理 -- DynamicProxyImpl实现类 
 * @description DynamicProxyImpl，在代理模式中，叫做委托类，包含业务逻辑
 * @description 一个接口实现类
 */
public class DynamicProxyImpl implements DynamicProxyInterface{

	public void queryInfo(){
		System.out.println("代理模式 之 JDK的动态代理 -- DynamicProxyImpl实现类   查看信息...");
	}
}
