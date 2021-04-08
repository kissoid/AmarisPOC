package com.turkcell.poc.repository;

import com.turkcell.poc.document.RequestLog;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogRepository extends ReactiveCrudRepository<RequestLog, String> {
    
    
}
