package com.assessment.event_gateway_service.config;

import jakarta.servlet.*;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class TraceIdFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        String traceId =
                UUID.randomUUID().toString();

        httpRequest.setAttribute(
                "TRACE_ID",
                traceId);

        System.out.println(
                "TRACE_ID : " + traceId);

        chain.doFilter(
                request,
                response);

    }

}