package be.ucll.java.ent;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter("/*")
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest){
            try {
                printRequest((HttpServletRequest) request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Nothing particular here
    }

    /**
     * Prints the request.
     *
     * @param httpServletRequest the http servlet request
     */
    private void printRequest(final HttpServletRequest httpServletRequest) {
        if (httpServletRequest == null) {
            return;
        }
        System.out.println("----------------------------------------");
        System.out.println("HttpServletRequest");
        System.out.println("\tRequestURL: " + httpServletRequest.getRequestURL());
        System.out.println("\tRequestURI: " + httpServletRequest.getRequestURI());
        System.out.println("\tScheme: " + httpServletRequest.getScheme());
        System.out.println("\tAuthType: " + httpServletRequest.getAuthType());
        System.out.println("\tEncoding: " + httpServletRequest.getCharacterEncoding());
        System.out.println("\tContentLength: " + httpServletRequest.getContentLength());
        System.out.println("\tContentType: " + httpServletRequest.getContentType());
        System.out.println("\tContextPath: " + httpServletRequest.getContextPath());
        System.out.println("\tMethod: " + httpServletRequest.getMethod());
        System.out.println("\tPathInfo: " + httpServletRequest.getPathInfo());
        System.out.println("\tProtocol: " + httpServletRequest.getProtocol());
        System.out.println("\tQuery: " + httpServletRequest.getQueryString());
        System.out.println("\tRemoteAddr: " + httpServletRequest.getRemoteAddr());
        System.out.println("\tRemoteHost: " + httpServletRequest.getRemoteHost());
        System.out.println("\tRemotePort: " + httpServletRequest.getRemotePort());
        System.out.println("\tRemoteUser: " + httpServletRequest.getRemoteUser());
        System.out.println("\tSessionID: " + httpServletRequest.getRequestedSessionId());
        System.out.println("\tServerName: " + httpServletRequest.getServerName());
        System.out.println("\tServerPort: " + httpServletRequest.getServerPort());
        System.out.println("\tServletPath: " + httpServletRequest.getServletPath());
        System.out.println("\tDispatcherType: " + httpServletRequest.getDispatcherType());
        System.out.println("\tLocalAddr: " + httpServletRequest.getLocalAddr());
        System.out.println("\tLocale: " + httpServletRequest.getLocale());
        System.out.println("\tLocalPort: " + httpServletRequest.getLocalPort());

        System.out.println("");
        int i = 0;
        if (httpServletRequest.getCookies() != null) {
            System.out.println("\tCookies");
            for (Cookie cookie : httpServletRequest.getCookies()) {
                System.out.println("\t  Cookie[" + i + "].name: " + cookie.getName());
                System.out.println("\t  Cookie[" + i + "].comment: " + cookie.getComment());
                System.out.println("\t  Cookie[" + i + "].domain: " + cookie.getDomain());
                System.out.println("\t  Cookie[" + i + "].maxAge: " + cookie.getMaxAge());
                System.out.println("\t  Cookie[" + i + "].path: " + cookie.getPath());
                System.out.println("\t  Cookie[" + i + "].secured: " + cookie.getSecure());
                System.out.println("\t  Cookie[" + i + "].value: " + cookie.getValue());
                System.out.println("\t  Cookie[" + i + "].version: " + cookie.getVersion());
                i++;
            }
        } else {
            System.out.println("\tNo Cookies");
        }

        System.out.println("");
        System.out.println("\tHeaders");
        int j = 0;
        final Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String header = httpServletRequest.getHeader(headerName);
            System.out.println("\t  Header[" + j + "].name: " + headerName);
            System.out.println("\t  Header[" + j + "].value: " + header);
            j++;
        }

        System.out.println("");
        System.out.println("\tParameters");
        int k = 0;
        final Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = httpServletRequest.getParameter(paramName);
            System.out.println("\t  Param[" + k + "].name: " + paramName);
            System.out.println("\t  Param[" + k + "].value: " + paramValue);
            k++;
        }

        // Print session information
        System.out.println("");
        printSession(httpServletRequest.getSession());

        System.out.println("----------------------------------------");
    }

    /**
     * Prints the session.
     *
     * @param session the session
     */
    private void printSession(final HttpSession session) {
        if (session == null) {
            System.err.println("No session");
            return;
        }
        System.out.println("\tSession Attributes");
        System.out.println("\t  Session.id: " + session.getId());
        System.out.println("\t  Session.creationTime: " + session.getCreationTime());
        System.out.println("\t  Session.lastAccessTime: " + session.getLastAccessedTime());
        System.out.println("\t  Session.maxInactiveInterval: " + session.getMaxInactiveInterval());

        int k = 0;
        final Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            final String paramName = attributeNames.nextElement();
            final Object paramValue = session.getAttribute(paramName);
            System.out.println("\t  Session Attribute[" + k + "].name: " + paramName);
            if (paramValue.getClass() != null) {
                System.out.println("\t  Session Attribute[" + k + "].class: " + paramValue.getClass());
            }
            System.out.println("\t  Session Attribute[" + k + "].value: " + paramValue);
            k++;
        }

    }
}
