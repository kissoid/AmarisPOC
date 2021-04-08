package com.turkcell.poc.repository;

import com.turkcell.poc.document.Menu;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends ReactiveCrudRepository<Menu, Long> {
    
    
}
