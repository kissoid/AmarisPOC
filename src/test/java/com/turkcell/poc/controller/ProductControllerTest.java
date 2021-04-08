package com.turkcell.poc.controller;

import com.turkcell.poc.document.Product;
import com.turkcell.poc.dto.ProductDTO;
import com.turkcell.poc.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
@WebFluxTest(controllers = ProductController.class)
@Import({ProductService.class, ModelMapper.class})
public class ProductControllerTest {

    @MockBean
    ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WebTestClient webClient;

    @Test
    void getProductList_test() {
        List<Product> productList = prepareProductList();
        Flux<Product> productFlux = Flux.fromIterable(productList);

        Mockito.doReturn(productFlux).when(productService).getProductList(0, 20);

        Flux<ProductDTO> dtoFlux = webClient.get().uri("/api/v1/products/getProductList?pageIndex=0&pageSize=20").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .returnResult(ProductDTO.class)
                .getResponseBody();

        ProductDTO productDTO = dtoFlux.blockFirst();

        Assertions.assertTrue(Objects.nonNull(productDTO));
        Assertions.assertEquals(1L, productDTO.getId());
        Assertions.assertEquals("Test User", productDTO.getUsername());

    }

    @Test
    void updateProductInfo_test() {
        List<Product> productList = prepareProductList();

        List<ProductDTO> productDTOList = prepareProductDTOListFromProductList(productList);

        Flux<Product> productFlux = Flux.fromIterable(productList);

        Mockito.doReturn(productFlux).when(productService).updateProductInfo(productList);

        Flux<ProductDTO> dtoFlux = webClient
                .put()
                .uri("/api/v1/products/updateProductInfo")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .bodyValue(productDTOList)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .returnResult(ProductDTO.class)
                .getResponseBody();

        ProductDTO productDTO = dtoFlux.blockFirst();

        Assertions.assertTrue(Objects.nonNull(productDTO));
        Assertions.assertEquals(1L, productDTO.getId());
        Assertions.assertEquals("Test User", productDTO.getUsername());

    }

    private List<Product> prepareProductList() {
        Product product = new Product();
        product.setId(1L);
        product.setLine("Hat");
        product.setLineType("Hat Tipi");
        product.setMobileNumber("05325554433");
        product.setShortNumber(1234);
        product.setUsername("Test User");

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        return productList;
    }

    private List<ProductDTO> prepareProductDTOListFromProductList(List<Product> productList) {
        return productList
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }


}
