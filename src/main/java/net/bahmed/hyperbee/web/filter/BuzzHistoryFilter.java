package net.bahmed.hyperbee.web.filter;

import net.bahmed.hyperbee.utils.constant.Messages;
import net.bahmed.hyperbee.web.security.AuthUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static net.bahmed.hyperbee.utils.constant.Constant.AUTH_USER_ATTRIBUTE;
import static net.bahmed.hyperbee.utils.constant.Url.DONE_URL;

/**
 * @author zoha
 * @since 12/1/16
 */
public class BuzzHistoryFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest)request).getSession();
        AuthUser authUser = (AuthUser) session.getAttribute(AUTH_USER_ATTRIBUTE);

        if(authUser != null && authUser.isAdmin()) {
            chain.doFilter(request, response);
            return;
        }

        session.setAttribute("message", Messages.BUZZ_ACCESS_DENIED);
        session.setAttribute("htmlTitle", Messages.TITLE_ACCESS_DENIED);
        session.setAttribute("messageStyle", "alert alert-danger");

        ((HttpServletResponse)response).sendRedirect(DONE_URL);
    }

    @Override
    public void destroy() {

    }
}
