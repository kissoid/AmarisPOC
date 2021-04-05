/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turkcell.poc.repository;

import com.turkcell.poc.entity.Menu;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kissoid
 */
@Repository
public interface MenuRepository extends ReactiveCrudRepository<Menu, Long> {
    
    
}
