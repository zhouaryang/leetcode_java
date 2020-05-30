package src.com.designModel.proxy.dynamicproxy;

public class DynamicProxyTest {

	/*
     *    JDK的动态代理依靠具体的接口，如果有些类并没有实现，则不能使用JDK的动态代理，这时候就需要
     * 使用CGLIB的动态代理
     * // JDK的动态代理的关键是InvocationHandler接口
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 DynamicProxy dynamicProxy = new DynamicProxy();
	        DynamicProxyInterface dynamicProxyForImpl = (DynamicProxyInterface) dynamicProxy.dynamicBindImplToProxyClass(new DynamicProxyImpl());
	        dynamicProxyForImpl.queryInfo();

	}

}
