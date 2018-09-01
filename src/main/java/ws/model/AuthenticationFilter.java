package ws.model;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet filter that checks user's credentials
 * in order to provide access to a resources.
 *
 * @author Zhasmina Stoyanova
 * @version 1.0 August 2018
 */
public class AuthenticationFilter implements javax.servlet.Filter {
    private static final String AUTHENTICATION_HEADER = "Authorization";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse res,
                         FilterChain filter) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            //the client sends the credentials by using the Authorization header
            String credentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

            //authenticates user
            Authentication auth = new Authentication();
            boolean authenticationSuccessful = auth.authenticate(credentials);

            if (authenticationSuccessful) {
                //process the request if the user is authorized
                filter.doFilter(request, res);
            } else {
                if (res instanceof HttpServletResponse) {
                    //send 401 response that the user is not authorized to access the resource
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