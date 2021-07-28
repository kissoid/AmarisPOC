package com.turkcell.poc.service;

import com.turkcell.poc.entity.RequestLog;
import com.turkcell.poc.repository.RequestLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class RequestLogService {
    
    private final RequestLogRepository requestLogRepository;
    
    public RequestLogService(RequestLogRepository requestLogRepository){
        this.requestLogRepository = requestLogRepository;
    }
    
    public Flux<RequestLog> getRequestLogList(){
        return requestLogRepository.findAll();
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<RequestLog> createRequestLog(RequestLog requestLog){
        return requestLogRepository.save(requestLog);
    }

    public Mono<RequestLog> getRequestLogById(String requestLogId){
        return requestLogRepository.findById(requestLogId);
    }
    
}
