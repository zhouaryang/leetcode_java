package src.com.designModel.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @description 代理模式 之 JDK的动态代理 -- DynamicProxy代理类
 * @description DynamicProxy，在代理模式中，叫做代理类，增强DynamicProxyImpl实现类 
 * @description 一个代理类，实现了InvocationHandler接口
 * @description JDK的动态代理 与 静态代理的区别就在这个代理类，静态代理类直接实现接口，而JDK的动态代理类不
 * 直接实现具体的接口，而是实现了JDK提供的InvocationHandler接口。
 */
public class DynamicProxy implements InvocationHandler{

	 // 目标实现类，也就是某个接口的实现类，这里的target_interface_implements，可以看做是一种抽象
    private Object target_interface_implements;
    // 带参数构造方法，把目标实现类的对象作为参数传递进去，这里不是覆盖具体某个实现类的构造方法，而是抽象出来一个方法
    public Object dynamicBindImplToProxyClass(Object target_interface_implements){
        this.target_interface_implements = target_interface_implements;
        // 取得代理对象
        return Proxy.newProxyInstance(target_interface_implements.getClass().getClassLoader(), 
                target_interface_implements.getClass().getInterfaces(), this);
        // JDK的动态代理依赖具体的接口，需要绑定接口，如果一个类没有实现接口，则不能使用JDK的动态代理
    }
	@Override
	 public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO Auto-generated method stub
        Object result = null;
        // 事务处理之前，可以执行一段代码
        System.out.println("事务处理之前...");
        // 执行方法
        result = method.invoke(target_interface_implements, args);
        // 事务处理之后，可以执行一段代码
        System.out.println("事务处理之后...");
        return result;
    }

	
}
