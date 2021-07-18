package others.reflect.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {

    private Object realSubject;

    public ProxyHandler(Object realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 在调用具体目标对象前，可以执行一些具体处理
        System.out.println("执行具体对象前...");
        Object result = method.invoke(realSubject, args);
        System.out.println("执行具体对象后...");
        return result;
    }
}
