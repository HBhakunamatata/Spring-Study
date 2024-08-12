package cloud.popples.overview.aop.eglog.method_2;


import cloud.popples.overview.aop.eglog.UserServlet;

public class ServletImpl02 implements UserServlet {
    @Override
    public void add() {
        System.out.println("ServletImpl02 " + "add()...");
    }

    @Override
    public void delete() {
        System.out.println("ServletImpl02 " + "delete()...");
    }

    @Override
    public void update() {
        System.out.println("ServletImpl02 " + "update()...");
    }

    @Override
    public void search() {
        System.out.println("ServletImpl02 " + "search()...");
    }
}
