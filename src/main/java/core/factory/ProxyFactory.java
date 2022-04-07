package core.factory;

import core.util.TransactionUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private ProxyFactory(){
    }

    private static ProxyFactory proxyFactory = new ProxyFactory();

    public static ProxyFactory getInstance(){
        return proxyFactory;
    }

    public Object getProxyObj(Object obj){
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try{
                    TransactionUtil.open();

                    result = method.invoke(proxy, args);

                    TransactionUtil.commit();
                }catch (Exception e){
                    e.printStackTrace();
                    TransactionUtil.rollback();
                }finally {
                    TransactionUtil.close();
                }
                return result ;
            }
        });
    }
}
