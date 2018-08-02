package ws.model;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements javax.servlet.Filter {
    public static final String AUTHENTICATION_HEADER = "Authorization";

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse res,
                         FilterChain filter) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String credentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

            Authentication auth = new Authentication();
            boolean authenticationSuccessful = auth.authenticate(credentials);

            if (authenticationSuccessful) {
                filter.doFilter(request, res);
            } else {
                if (res instanceof HttpServletResponse) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) res;
                    httpServletResponse
                            .setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}