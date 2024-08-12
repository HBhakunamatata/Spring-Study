package cloud.popples.overview.aop.eglog.method_3;


import cloud.popples.overview.aop.eglog.UserServlet;

public class ServletImpl03 implements UserServlet {
    @Override
    public void add() {
        System.out.println("ServletImpl03 " + "add()...");
    }

    @Override
    public void delete() {
        System.out.println("ServletImpl03 " + "delete()...");
    }

    @Override
    public void update() {
        System.out.println("ServletImpl03 " + "update()...");
    }

    @Override
    public void search() {
        System.out.println("ServletImpl03 " + "search()...");
    }
}
