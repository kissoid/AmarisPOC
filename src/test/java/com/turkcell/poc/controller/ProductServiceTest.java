package com.turkcell.poc.controller;

import com.turkcell.poc.document.Product;
import com.turkcell.poc.repository.ProductRepository;
import com.turkcell.poc.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@Import(ProductService.class)
public class ProductServiceTest {

    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductService productService;



    @Test
    void updateProductInfoService_test() {
        List<Product> productList = prepareProductList();

        Product product = productList.iterator().next();
        Mono<Product> productMono = Mono.just(copyAndModifyProduct(productList.iterator().next()));

        Mockito.doReturn(productMono).when(productRepository).save(product);
        Flux<Product> productFlux = productService.updateProductInfo(productList);

        Product savedProduct = productFlux.map(item->item).next().block();
        
        Assertions.assertTrue(Objects.nonNull(savedProduct));
        Assertions.assertFalse(Objects.equals(savedProduct.getShortNumber(), product.getShortNumber()));

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

    private Product copyAndModifyProduct(Product product) {
        Product modifiedProduct = new Product();
        modifiedProduct.setId(product.getId());
        modifiedProduct.setLine(product.getLine());
        modifiedProduct.setLineType(product.getLineType());
        modifiedProduct.setMobileNumber("05325554433");
        modifiedProduct.setShortNumber(ThreadLocalRandom.current().nextInt(1000, 10000));
        modifiedProduct.setUsername(product.getUsername());
        return modifiedProduct;
    }
}
