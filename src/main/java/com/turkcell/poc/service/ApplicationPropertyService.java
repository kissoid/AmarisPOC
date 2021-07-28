package com.turkcell.poc.service;

import com.turkcell.poc.entity.ApplicationProperty;
import com.turkcell.poc.repository.ApplicationPropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ApplicationPropertyService {
    
    private static final Logger logger = LoggerFactory.getLogger(ApplicationPropertyService.class);

    private final ApplicationPropertyRepository applicationPropertyRepository;

    public ApplicationPropertyService(ApplicationPropertyRepository applicationPropertyRepository) {
        this.applicationPropertyRepository = applicationPropertyRepository;
    }

    public Flux<ApplicationProperty> getApplicationPropertyList(int pageIndex, int pageSize) {
        logger.info("Fetch process started");
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        return applicationPropertyRepository.findByIdNotNull(pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<ApplicationProperty> updateApplicationProperty(ApplicationProperty applicationProperty) {
        logger.info("Update process started");
        return applicationPropertyRepository.save(applicationProperty);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<ApplicationProperty> createApplicationProperty(ApplicationProperty applicationProperty){
        logger.info("Create process started");
        return applicationPropertyRepository.save(applicationProperty);
    }

    public Mono<ApplicationProperty> getProductById(Long productId){
        return applicationPropertyRepository.findById(productId);
    }
    
}
