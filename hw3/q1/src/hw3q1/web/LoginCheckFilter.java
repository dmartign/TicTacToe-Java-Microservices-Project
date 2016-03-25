package hw3q1.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCheckFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(LoginCheckFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            LOGGER.info(String.format("Not logged in: %s", httpServletRequest.getRequestURI()));
            httpServletResponse.sendRedirect("login.html");
        } else {
            LOGGER.info(String.format("Logged in: %s", httpServletRequest.getRequestURI()));
            // Boilerplate to keep processing filters
            fc.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
