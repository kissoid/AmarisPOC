/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turkcell.poc.repository;

import com.turkcell.poc.dto.ProductDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 *
 * @author kissoid
 */
@Repository
public interface ProductRepository extends ReactiveCrudRepository<ProductDTO, Long> {
    
    Flux<ProductDTO> findProducts(Pageable pageable);
    
    
}
