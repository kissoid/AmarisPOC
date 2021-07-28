package com.turkcell.poc.repository;

import com.turkcell.poc.entity.ApplicationProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ApplicationPropertyRepository extends ReactiveCrudRepository<ApplicationProperty, Long> {

    Flux<ApplicationProperty> findByIdNotNull(Pageable pageable);
    
}
