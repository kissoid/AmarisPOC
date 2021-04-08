package com.turkcell.poc.repository;

import com.turkcell.poc.document.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveSortingRepository<Product, Long> {

    Flux<Product> findByIdNotNull(Pageable pageable);
    
    
}
