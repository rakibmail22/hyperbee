package net.bahmed.hyperbee.web.filter;

import net.bahmed.hyperbee.web.security.AuthUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static net.bahmed.hyperbee.utils.constant.Messages.NOTICE_ACCESS_DENIED;
import static net.bahmed.hyperbee.utils.constant.Url.DONE_URL;

/**
 * @author rumman
 * @since 11/29/16
 */
public class NoticeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");

        if (authUser != null && authUser.isAdmin()) {
            chain.doFilter(request, response);
            return;
        }

        session.setAttribute("htmlTitle", NOTICE_ACCESS_DENIED);
        session.setAttribute("message", NOTICE_ACCESS_DENIED);
        session.setAttribute("messageStyle", "alert alert-danger");

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect(DONE_URL);
    }

    @Override
    public void destroy() {

    }
}
