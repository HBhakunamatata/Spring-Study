package cloud.popples.overview.aop.preview.proxy;


import cloud.popples.overview.aop.preview.Rent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RentHandler implements InvocationHandler {

    private Object proxied;

    public void setProxied(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        showHouse();
        method.invoke(proxied, args);
        signContract();

        return null;
    }

    public void showHouse() {
        System.out.println("Show house to client");
    }

    public void signContract () {
        System.out.println("Sign a contract");
    }

    public Object getProxy () {
        return Proxy.newProxyInstance(
                Rent.class.getClassLoader(),
                new Class[] {Rent.class},
                this
        );
    }
}
