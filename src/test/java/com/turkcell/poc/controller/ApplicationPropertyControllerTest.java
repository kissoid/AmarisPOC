package com.turkcell.poc.controller;

import com.turkcell.poc.dto.ApplicationPropertyDTO;
import com.turkcell.poc.entity.ApplicationProperty;
import com.turkcell.poc.service.ApplicationPropertyService;
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
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ApplicationPropertyController.class)
@Import({ApplicationPropertyService.class, ModelMapper.class})
public class ApplicationPropertyControllerTest {

    @MockBean
    ApplicationPropertyService applicationPropertyService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WebTestClient webClient;

    @Test
    @WithMockUser(username = "Test User", authorities = {"USER"})
    void getApplicationPropertyList_test() {
        List<ApplicationProperty> applicationPropertyList = prepareApplicationPropertyList();
        Flux<ApplicationProperty> applicationPropertyFlux = Flux.fromIterable(applicationPropertyList);

        Mockito.doReturn(applicationPropertyFlux).when(applicationPropertyService).getApplicationPropertyList(0, 20);

        Flux<ApplicationPropertyDTO> dtoFlux = webClient.get().uri("/api/v1/applications/properties?pageIndex=0&pageSize=20").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .returnResult(ApplicationPropertyDTO.class)
                .getResponseBody();

        ApplicationPropertyDTO applicationPropertyDTO = dtoFlux.blockFirst();

        Assertions.assertTrue(Objects.nonNull(applicationPropertyDTO));
        Assertions.assertEquals(1L, applicationPropertyDTO.getId());
        Assertions.assertEquals("Test User", applicationPropertyDTO.getCreateUser());

    }

    @Test
    @WithMockUser(username = "Test User", authorities = {"USER"})
    void updateApplicationProperty_test() {
        ApplicationProperty applicationProperty = prepareApplicationPropertyForUpdate();

        ApplicationPropertyDTO applicationPropertyDTO = modelMapper.map(applicationProperty, ApplicationPropertyDTO.class);

        Mono<ApplicationPropertyDTO> applicationPropertyMono = Mono.just(applicationPropertyDTO);

        Mockito.doReturn(applicationPropertyMono).when(applicationPropertyService).updateApplicationProperty(applicationProperty);

        Flux<ApplicationPropertyDTO> dtoFlux = webClient
                .mutateWith(csrf())
                .put()
                .uri("/api/v1/applications/properties")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .bodyValue(applicationPropertyDTO)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .returnResult(ApplicationPropertyDTO.class)
                .getResponseBody();

        ApplicationPropertyDTO updatedApplicationPropertyDTO = dtoFlux.blockFirst();

        Assertions.assertTrue(Objects.nonNull(updatedApplicationPropertyDTO));
        Assertions.assertEquals(1L, updatedApplicationPropertyDTO.getId());
        Assertions.assertEquals("Test User", updatedApplicationPropertyDTO.getId());

    }

    @Test
    @WithMockUser(username = "Test User", authorities = {"USER"})
    void createApplicationProperty_test() {
        ApplicationProperty applicationProperty = prepareApplicationPropertyForCreate();

        ApplicationPropertyDTO applicationPropertyDTO = modelMapper.map(applicationProperty, ApplicationPropertyDTO.class);

        Mono<ApplicationPropertyDTO> applicationPropertyMono = Mono.just(applicationPropertyDTO);

        Mockito.doReturn(applicationPropertyMono).when(applicationPropertyService).updateApplicationProperty(applicationProperty);

        Flux<ApplicationPropertyDTO> dtoFlux = webClient
                .mutateWith(csrf())
                .put()
                .uri("/api/v1/applications/properties")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .bodyValue(applicationPropertyDTO)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .returnResult(ApplicationPropertyDTO.class)
                .getResponseBody();

        ApplicationPropertyDTO updatedApplicationPropertyDTO = dtoFlux.blockFirst();

        Assertions.assertTrue(Objects.nonNull(updatedApplicationPropertyDTO));
        Assertions.assertEquals(1L, updatedApplicationPropertyDTO.getId());
        Assertions.assertEquals("Test User", updatedApplicationPropertyDTO.getId());

    }
    
    private List<ApplicationProperty> prepareApplicationPropertyList() {
        ApplicationProperty applicationProperty = prepareApplicationProperty();
        List<ApplicationProperty> applicationPropertyList = new ArrayList<>();
        applicationPropertyList.add(applicationProperty);
        return applicationPropertyList;
    }

    private ApplicationProperty prepareApplicationProperty() {
        ApplicationProperty applicationProperty = new ApplicationProperty();
        applicationProperty.setId(1L);
        applicationProperty.setKey("xxxxxxxxxx");
        applicationProperty.setValue("ttttttttt");
        applicationProperty.setValueEncrypted("Y");
        applicationProperty.setOdmValue("bbb");
        applicationProperty.setOdmValueEncrypted("N");
        applicationProperty.setGpValue("ccc");
        applicationProperty.setGpValueEncrypted("Y");
        applicationProperty.setDescription("test description");
        applicationProperty.setCreateUser("Test User");

        return applicationProperty;
    }
    
    private ApplicationProperty prepareApplicationPropertyForUpdate() {
        ApplicationProperty applicationProperty = new ApplicationProperty();
        applicationProperty.setId(1L);
        applicationProperty.setKey("xxxxxxxxxx");
        applicationProperty.setValue("yyyyyyyyyyy");
        applicationProperty.setValueEncrypted("N");
        applicationProperty.setOdmValue("bbb");
        applicationProperty.setOdmValueEncrypted("N");
        applicationProperty.setGpValue("ccc");
        applicationProperty.setGpValueEncrypted("Y");
        applicationProperty.setDescription("test description");

        return applicationProperty;
    }

    private ApplicationProperty prepareApplicationPropertyForCreate() {
        ApplicationProperty applicationProperty = new ApplicationProperty();
        applicationProperty.setId(1L);
        applicationProperty.setKey("xxxxxxxxxx");
        applicationProperty.setValue("ttttttttt");
        applicationProperty.setValueEncrypted("Y");
        applicationProperty.setOdmValue("bbb");
        applicationProperty.setOdmValueEncrypted("N");
        applicationProperty.setGpValue("ccc");
        applicationProperty.setGpValueEncrypted("Y");
        applicationProperty.setDescription("test description");

        return applicationProperty;
    }
    
}
