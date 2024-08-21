package cloud.popples.securingweb.spittr.servlet;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    public MyFilter() {
    }
    public void destroy() {
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("myFilter");
        chain.doFilter(request, response);
    }
    public void init(FilterConfig fConfig) throws ServletException {
    }
}