package by.zhigarev.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private String encoding;
    private ServletContext context;
    private static final String CHARACTER_ENCODING = "characterEncoding";
    private static final String PARAMETER_ENCODING = "Parameter encoding = ";
    private static final String ENCODING_SET = "Encoding set: ";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(CHARACTER_ENCODING);
        context = filterConfig.getServletContext();
        context.log(PARAMETER_ENCODING + encoding);

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        req.setCharacterEncoding(encoding);
        resp.setCharacterEncoding(encoding);

        context.log(ENCODING_SET + encoding);
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

}
