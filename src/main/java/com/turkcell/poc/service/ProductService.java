package com.turkcell.poc.service;

import com.turkcell.poc.dto.ProductDTO;
import com.turkcell.poc.repository.ProductRepository;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<ProductDTO> getProductList(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        return productRepository.findProducts(pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Flux<ProductDTO> updateProductInfo(List<ProductDTO> productList) {
        return Flux.fromIterable(productList)
                .parallel(2)
                .runOn(Schedulers.parallel())
                .map(product -> {
                    product.setShortNumber(ThreadLocalRandom.current().nextInt());
                    return productRepository.save(product).block();
                }).sequential();
    }

}
