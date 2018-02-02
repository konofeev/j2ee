package konofeev.filter;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import javax.servlet.ServletException;
import java.util.logging.Logger;

@WebFilter("/*")
public class FilterTest implements Filter
{
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
    {
        Logger log = Logger.getLogger(FilterTest.class.getName());
        String servletPath = ((HttpServletRequest)request).getServletPath();
        log.info("Фильтр для сервлета: " + servletPath);
                                         

        // передаём управление далее
        filterChain.doFilter(request, response);
    }

    public void destroy()
    {
    }

    public void init(FilterConfig filterConfig) throws ServletException 
    {
    }
}
