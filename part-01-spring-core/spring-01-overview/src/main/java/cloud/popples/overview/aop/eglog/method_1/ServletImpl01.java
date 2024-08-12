package cloud.popples.overview.aop.eglog.method_1;

import cloud.popples.overview.aop.eglog.UserServlet;


public class ServletImpl01 implements UserServlet {
    @Override
    public void add() {
        System.out.println("ServletImpl01 " + "add()...");
    }

    @Override
    public void delete() {
        System.out.println("ServletImpl01 " + "delete()...");
    }

    @Override
    public void update() {
        System.out.println("ServletImpl01 " + "update()...");
    }

    @Override
    public void search() {
        System.out.println("ServletImpl01 " + "search()...");
    }
}
