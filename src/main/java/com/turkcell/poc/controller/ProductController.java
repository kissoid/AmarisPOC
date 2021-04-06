package com.turkcell.poc.controller;

import com.turkcell.poc.dto.ProductDTO;
import com.turkcell.poc.service.ProductService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/getProductList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Flux<ProductDTO> getProductList(
            @RequestParam("pageIndex") int pageIndex,
            @RequestParam("pageSize") int pageSize) {
        return productService.getProductList(pageIndex, pageSize)
                .map(product -> modelMapper.map(product, ProductDTO.class));
    }

    @RequestMapping(value = "/updateProductInfo", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public Flux<ProductDTO> updateProductInfo(@RequestBody List<ProductDTO> productList) {
        return productService.updateProductInfo(productList)
                .map(product -> modelMapper.map(product, ProductDTO.class));
    }

}
