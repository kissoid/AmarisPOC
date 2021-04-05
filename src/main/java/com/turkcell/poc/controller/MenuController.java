package com.turkcell.poc.controller;

import com.turkcell.poc.entity.Menu;
import com.turkcell.poc.service.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/menus")
public class MenuController {
    
    private final MenuService menuService;
    
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Flux<Menu> getMenuList() {
        return  menuService.getMenuList();

    }
    
}
