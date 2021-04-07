/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turkcell.poc.interceptor;

import com.turkcell.poc.document.RequestLog;
import com.turkcell.poc.document.RequestParameter;
import com.turkcell.poc.service.RequestLogService;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import reactor.core.scheduler.Schedulers;

/**
 *
 * @author kissoid
 */
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RequestLogService requestLogService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ContentCachingRequestWrapper requestCacheWrapper   = new ContentCachingRequestWrapper(request);
        
        RequestLog requestLog = new RequestLog();
        requestLog.setId(UUID.randomUUID().toString());
        requestLog.setService(requestCacheWrapper.getRequestURI()); 
        requestLog.setRequestBody(new String(requestCacheWrapper.getContentAsByteArray()));
        requestLog.setDate(new Date());
        requestLog.setRequestParameterList(requestCacheWrapper
                .getParameterMap()
                .entrySet()
                .stream()
                .map(entry->new RequestParameter(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));
        requestLogService.createRequestLog(requestLog).subscribeOn(Schedulers.parallel()).subscribe();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
