package com.kamalkavin96.filemanager.configurations;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ReqResLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request, 1024 * 1024);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            long duration = System.currentTimeMillis() - startTime;

            // =========================
            // REQUEST HEADERS
            // =========================

            Map<String, String> reqHeaders = getRequestHeaders(request);

            // =========================
            // RESPONSE HEADERS
            // =========================

            Map<String, String> resHeaders = getResponseHeaders(response);

            // =========================
            // REQUEST BODY
            // =========================

            String requestBody = getRequestBody(wrappedRequest);

            // =========================
            // RESPONSE BODY
            // =========================

            String responseBody = getResponseBody(wrappedResponse);

            log.info("""

                    ================= HTTP REQUEST =================
                    Method       : {}
                    URI          : {}
                    Headers      : {}
                    Query        : {}
                    Request Body : {}

                    ================= HTTP RESPONSE =================
                    Status       : {}
                    Headers      : {}
                    Response Body: {}
                    Duration     : {} ms

                    ==================================================
                    """,

                    request.getMethod(),
                    request.getRequestURI(),
                    reqHeaders,
                    request.getQueryString(),
                    requestBody,
                    response.getStatus(),
                    resHeaders,
                    responseBody,
                    duration
            );

            // IMPORTANT
            wrappedResponse.copyBodyToResponse();
        }
    }


    private Map<String, String> getRequestHeaders(HttpServletRequest request) {

        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                headers.put(headerName, headerValue);
            }
        }
        return headers;
    }


    private Map<String, String> getResponseHeaders(HttpServletResponse response) {

        Map<String, String> headers = new HashMap<>();
        Collection<String> headerNames = response.getHeaderNames();

        for (String headerName : headerNames) {
            String headerValue = response.getHeader(headerName);
            headers.put(headerName, headerValue);
        }
        return headers;
    }


    private String getRequestBody(ContentCachingRequestWrapper request) {

        byte[] content = request.getContentAsByteArray();
        if (content.length == 0) {
            return "";
        }
        return new String(content, StandardCharsets.UTF_8
        );
    }


    private String getResponseBody(ContentCachingResponseWrapper response) {

        byte[] content = response.getContentAsByteArray();
        if (content.length == 0) {
            return "";
        }
        return new String(content, StandardCharsets.UTF_8);
    }
}