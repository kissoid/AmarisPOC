package com.turkcell.poc.service;

import com.turkcell.poc.document.Menu;
import com.turkcell.poc.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class MenuService {
    
    private final MenuRepository menuRepository;
    
    public MenuService(MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }
    
    public Flux<Menu> getMenuList(){
        return menuRepository.findAll();
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<Menu> createMenu(Menu menu){
        return menuRepository.save(menu);
    }

    public Mono<Menu> getMenuById(Long menuId){
        return menuRepository.findById(menuId);
    }
    
}
