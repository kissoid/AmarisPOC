package com.turkcell.poc.controller;

import com.turkcell.poc.dto.MenuDTO;
import com.turkcell.poc.document.Menu;
import com.turkcell.poc.service.MenuService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = MenuController.class)
@Import({MenuService.class, ModelMapper.class})
public class MenuControllerTest {

    @MockBean
    MenuService menuService;
    
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private WebTestClient webClient;

    @Test
    void getMenuList_test() {

        Menu menu = new Menu();
        menu.setId(1L);
        menu.setName("Test menu");

        List<Menu> menuList = new ArrayList<>();
        menuList.add(menu);

        Flux<Menu> menuFlux = Flux.fromIterable(menuList);

        Mockito.doReturn(menuFlux).when(menuService).getMenuList();

        Flux<MenuDTO> dtoFlux = webClient.get().uri("/api/menus/getMenuList").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .returnResult(MenuDTO.class)
                .getResponseBody();
        
        MenuDTO menuDTO = dtoFlux.blockFirst();
        
        Assertions.assertTrue(Objects.nonNull(menuDTO));
        Assertions.assertEquals(1L, menuDTO.getId());
        Assertions.assertEquals("Test menu", menuDTO.getName());

    }

}
