package com.turkcell.poc.controller;

import com.turkcell.poc.dto.ApplicationPropertyDTO;
import com.turkcell.poc.entity.ApplicationProperty;
import com.turkcell.poc.helper.ApplicationPropertyHelper;
import com.turkcell.poc.service.ApplicationPropertyService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1")
public class ApplicationPropertyController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationPropertyController.class);
    private final ApplicationPropertyService applicationPropertyService;
    private final ModelMapper modelMapper;

    public ApplicationPropertyController(ApplicationPropertyService productService, ModelMapper modelMapper) {
        this.applicationPropertyService = productService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/applications/properties", method = RequestMethod.GET, produces = "application/json")
    public Flux<ApplicationPropertyDTO> getApplicationPropertyList(
            @RequestParam("pageIndex") int pageIndex,
            @RequestParam("pageSize") int pageSize) {
        logger.info("Application property list requested");
        return applicationPropertyService.getApplicationPropertyList(pageIndex, pageSize)
                .map(product -> modelMapper.map(product, ApplicationPropertyDTO.class))
                .map(ApplicationPropertyHelper::prepareForGet);
    }

    @RequestMapping(value = "/applications/properties", method = RequestMethod.PUT, produces = "application/json")
    public Mono<ApplicationPropertyDTO> updateApplicationProperty(Authentication authentication, @RequestBody ApplicationPropertyDTO applicationPropertyDTO) {
        logger.info("Application property update requested");
        ApplicationProperty applicationProperty = modelMapper.map(applicationPropertyDTO, ApplicationProperty.class);
        ApplicationPropertyHelper.prepareForUpdate(applicationProperty, authentication);
        return applicationPropertyService.updateApplicationProperty(applicationProperty)
                .map(property -> modelMapper.map(property, ApplicationPropertyDTO.class));

    }

    @RequestMapping(value = "/applications/properties", method = RequestMethod.POST, produces = "application/json")
    public Mono<ApplicationPropertyDTO> createApplicationProperty(Authentication authentication, @RequestBody ApplicationPropertyDTO applicationPropertyDTO) {
        logger.info("Application property create requested");
        ApplicationProperty applicationProperty = modelMapper.map(applicationPropertyDTO, ApplicationProperty.class);
        ApplicationPropertyHelper.prepareForCreate(applicationProperty, authentication);
        return applicationPropertyService.createApplicationProperty(applicationProperty)
                .map(property -> modelMapper.map(property, ApplicationPropertyDTO.class));

    }
    
}
