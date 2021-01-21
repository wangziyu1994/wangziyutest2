package com.wangziyu.proxystudy.test;

import com.wangziyu.proxystudy.abstractmodel.AInterface;
import com.wangziyu.proxystudy.abstractmodel.BInterface;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyTest {
    @Test
    public void test1(){
        //类加载器
        ClassLoader classLoader=this.getClass().getClassLoader();
        //
        InvocationHandler invocationHandler=new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("进入代理对象的实现方法");
                String str="";
                if(args!=null&&args.length>0){
                    for(int i=0;i<=args.length-1;i++) {
                        System.out.println("第"+i+"个参数:"+args[i]);
                        str=(String)args[i];
                    }
                }
                return str;
            }
        };
        //需要代理类的Class对象数组
        Class[] proxyClassArray={AInterface.class, BInterface.class};
        Object obj= Proxy.newProxyInstance(classLoader,proxyClassArray,invocationHandler);
        AInterface a=(AInterface)obj;
        BInterface b=(BInterface)obj;

        String resultA=a.function1("AInterface");
        String resultB=b.function2("BInterface");
        System.out.println(a.getClass());
        System.out.println(b.getClass());
        System.out.println(obj.getClass());
        System.out.println(resultA);
        System.out.println(resultB);

    }



    @Test
    public void test2() throws Exception{
       String str="は";
       System.out.println("GBK  "+str.getBytes("GBK").length);
        System.out.println("UTF-8  "+str.getBytes("UTF-8").length);
    }
}
