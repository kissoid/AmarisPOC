package com.turkcell.poc.controller;

import com.turkcell.poc.entity.ApplicationProperty;
import com.turkcell.poc.repository.ApplicationPropertyRepository;
import com.turkcell.poc.service.ApplicationPropertyService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@Import(ApplicationPropertyService.class)
public class ApplicationPropertyServiceTest {

    @MockBean
    ApplicationPropertyRepository applicationPropertyRepository;

    @Autowired
    ApplicationPropertyService applicationPropertyService;



    @Test
    void updateApplicationPropertyService_test() {
        List<ApplicationProperty> applicationPropertyList = prepareApplicationPropertyList();

        ApplicationProperty applicationProperty = applicationPropertyList.iterator().next();
        Mono<ApplicationProperty> applicationPropertyMono = Mono.just(copyAndModifyApplicationProperty(applicationPropertyList.iterator().next()));

        Mockito.doReturn(applicationPropertyMono).when(applicationPropertyRepository).save(applicationProperty);
        Mono<ApplicationProperty> updatedApplicationPropertyMono = applicationPropertyService.updateApplicationProperty(applicationProperty);

        ApplicationProperty savedApplicationProperty = updatedApplicationPropertyMono.map(item->item).block();
        
        Assertions.assertTrue(Objects.nonNull(savedApplicationProperty));
        Assertions.assertTrue(Objects.equals(savedApplicationProperty.getId(), applicationProperty.getId()));

    }

        private ApplicationProperty prepareApplicationProperty() {
        ApplicationProperty applicationProperty = new ApplicationProperty();
        applicationProperty.setId(1L);
        applicationProperty.setId(1L);
        applicationProperty.setKey("xxxxxxxxxx");
        applicationProperty.setValue("ttttttttt");
        applicationProperty.setValueEncrypted("Y");
        applicationProperty.setOdmValue("bbb");
        applicationProperty.setOdmValueEncrypted("N");
        applicationProperty.setGpValue("ccc");
        applicationProperty.setGpValueEncrypted("Y");
        applicationProperty.setCreateUser("Test User");
        applicationProperty.setCreateDate(new Date());
        applicationProperty.setUpdateUser("");
        applicationProperty.setUpdateDate(null);
        applicationProperty.setDescription("");

        return applicationProperty;
    }
    
    private List<ApplicationProperty> prepareApplicationPropertyList() {
        ApplicationProperty applicationProperty = prepareApplicationProperty();

        List<ApplicationProperty> applicationPropertyList = new ArrayList<>();
        applicationPropertyList.add(applicationProperty);
        return applicationPropertyList;
    }

    private ApplicationProperty copyAndModifyApplicationProperty(ApplicationProperty applicationProperty) {
        ApplicationProperty modifiedApplicationProperty = new ApplicationProperty();
        modifiedApplicationProperty.setId(applicationProperty.getId());
        modifiedApplicationProperty.setKey(applicationProperty.getKey());
        modifiedApplicationProperty.setValue(applicationProperty.getValue());
        modifiedApplicationProperty.setValueEncrypted(applicationProperty.getValueEncrypted());
        modifiedApplicationProperty.setOdmValue(applicationProperty.getOdmValue());
        modifiedApplicationProperty.setOdmValueEncrypted(applicationProperty.getOdmValueEncrypted());
        modifiedApplicationProperty.setGpValue(applicationProperty.getGpValue());
        modifiedApplicationProperty.setGpValueEncrypted(applicationProperty.getGpValueEncrypted());
        modifiedApplicationProperty.setCreateUser(applicationProperty.getCreateUser());
        modifiedApplicationProperty.setCreateDate(applicationProperty.getCreateDate());
        modifiedApplicationProperty.setUpdateUser(applicationProperty.getUpdateUser());
        modifiedApplicationProperty.setUpdateDate(applicationProperty.getUpdateDate());
        modifiedApplicationProperty.setDescription(applicationProperty.getDescription());
        return modifiedApplicationProperty;
    }
}
