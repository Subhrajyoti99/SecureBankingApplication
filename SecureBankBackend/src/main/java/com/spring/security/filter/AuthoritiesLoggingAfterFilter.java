package com.spring.security.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Slf4j
public class AuthoritiesLoggingAfterFilter implements Filter {

    /**
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this filter to pass the request and response
     *                 to for further processing
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            log.info("User " + authentication.getName() + " is successfully authenticated and "
                    + "has authorities " + authentication.getAuthorities().toString()); // logging the username
        }
        chain.doFilter(request, response); //transferring control to next filter
    }
}