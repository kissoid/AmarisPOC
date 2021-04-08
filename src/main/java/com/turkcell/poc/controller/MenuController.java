package com.turkcell.poc.controller;

import com.turkcell.poc.dto.MenuDTO;
import com.turkcell.poc.service.MenuService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/v1/menus")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    
    private final MenuService menuService;
    private final ModelMapper modelMapper;

    public MenuController(MenuService menuService, ModelMapper modelMapper) {
        this.menuService = menuService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value="getMenuList", method = RequestMethod.GET, produces = "application/json")
    public Flux<MenuDTO> getMenuList() {
        logger.info("Menu list requested");
        return menuService.getMenuList()
                .map(menu -> modelMapper.map(menu, MenuDTO.class));

    }

}
