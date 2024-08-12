package cloud.popples.overview.dioc.othereg.Servlet;


import cloud.popples.overview.dioc.othereg.dao.EmpDao;

public class EmpServletImpl implements EmpServlet {

    /* 原来的方法：强耦合
      Servlet层想要使用Dao层的实现类时需要
      在Servlet实现类中创建DaoImpl对象,
    EmpDao empDao = new EmpDaoMySQL();
      但是，如果使用不同的DaoImpl那么就需要
      创建新的EmpImpl对象
    EmpDao empDao2 = new EmpDaoOracle();
      问题：EmpImpl对象在Servlet中是写死的，
        不能动态创建，修改代码的代价非常大。
     */

    // 所以需要使用动态创建的方式：IoC
    // 思想：EmpImpl对象的创建信息从外部给入(如xml或者注解等)
    // 这样EmpImpl对象创建权从Servlet层上移给用户层

    // 具体方式：在用户和Servlet层之间创建IoC容器托管对象的创建
    // 用户根据需要的功能要求IoC容器创建对应的对象就好了

    // 总的来说，原来是对象的创建写死在程序当中的，
    // 也就是程序完全控制对象的创建（主动依赖）
    // 现在对象的创建权给用户控制了（反向依赖）
    // 这样代码由用户注入，Servlet层代码就不需要大量修改


    // 2. IoC方式
    private EmpDao empDao;

    public void setEmpDao(EmpDao empDao) {
        this.empDao = empDao;
    }

    public void getEmp() {
        empDao.getEmp();
    }
}
