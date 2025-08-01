package com.spring.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class CsrfCookieFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        //getting the csrf token by taking the object of csrf token
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        //Render the token value to a cookie by causing deferred token to be loaded
        csrfToken.getToken();
        filterChain.doFilter(request, response);

    }
}
