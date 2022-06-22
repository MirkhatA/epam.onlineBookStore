package com.epam.bookstore.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LanguageFilter implements Filter {
    private String defaultLanguageName;
    private Integer defaultLanguageId;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        defaultLanguageName = filterConfig.getInitParameter("defaultLanguageName");
        defaultLanguageId = Integer.valueOf(filterConfig.getInitParameter("defaultLanguageId"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();

        String languageName = (String) session.getAttribute("languageName");


        if (languageName == null) {
            session.setAttribute("languageName", defaultLanguageName);
            session.setAttribute("languageId", defaultLanguageId);
        } else if (languageName.equals("en")) {
            session.setAttribute("languageName", "en");
            session.setAttribute("languageId", 1);
        } else if (languageName.equals("ru")) {
            session.setAttribute("languageName", "ru");
            session.setAttribute("languageId", 2);
        }

        servletResponse.setContentType("text/html;charset=UTF-8");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
