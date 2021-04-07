
package com.turkcell.poc.constoller;

import com.turkcell.poc.controller.MenuController;
import com.turkcell.poc.repository.MenuRepository;
import com.turkcell.poc.service.MenuService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = MenuController.class)
@Import(MenuService.class)
public class MenuControllerTest {
   
@MockBean
    MenuRepository repository;
 
    @Autowired
    private WebTestClient webClient;
 
    @Test
    void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Test");
        employee.setSalary(1000);
 
        Mockito.when(repository.save(employee)).thenReturn(Mono.just(employee));
 
        webClient.post()
            .uri("/create")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(employee))
            .exchange()
            .expectStatus().isCreated();
 
        Mockito.verify(repository, times(1)).save(employee);
    }
    
}
