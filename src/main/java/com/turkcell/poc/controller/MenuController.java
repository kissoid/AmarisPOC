package com.turkcell.poc.controller;

import com.turkcell.poc.dto.MenuDTO;
import com.turkcell.poc.service.MenuService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/menus")
public class MenuController {

    private final MenuService menuService;
    private final ModelMapper modelMapper;

    public MenuController(MenuService menuService, ModelMapper modelMapper) {
        this.menuService = menuService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Flux<MenuDTO> getMenuList() {
        return menuService.getMenuList()
                .map(menu -> modelMapper.map(menu, MenuDTO.class));

    }

}
