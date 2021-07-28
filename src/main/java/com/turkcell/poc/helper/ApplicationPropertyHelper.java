/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turkcell.poc.helper;

import com.turkcell.poc.dto.ApplicationPropertyDTO;
import com.turkcell.poc.entity.ApplicationProperty;
import com.turkcell.poc.enumerator.YesNoEnum;
import com.turkcell.poc.util.EncryptionUtil;
import com.turkcell.poc.util.StringUtil;
import java.util.Date;
import org.springframework.security.core.Authentication;

/**
 *
 * @author kissoid
 */
public class ApplicationPropertyHelper {
    
    private ApplicationPropertyHelper(){}
    
    
    public static void prepareForCreate(ApplicationProperty applicationProperty, Authentication authentication){
        applicationProperty.setCreateUser((String)authentication.getPrincipal());
        applicationProperty.setCreateDate(new Date());
        if(!StringUtil.isBlankOrNull(applicationProperty.getValue()) && StringUtil.equals(applicationProperty.getValueEncrypted(), YesNoEnum.YES.getValue())){
            applicationProperty.setValue(EncryptionUtil.encrypt(applicationProperty.getValue()));
        }
        if(!StringUtil.isBlankOrNull(applicationProperty.getOdmValue()) && StringUtil.equals(applicationProperty.getOdmValueEncrypted(), YesNoEnum.YES.getValue())){
            applicationProperty.setOdmValue(EncryptionUtil.encrypt(applicationProperty.getOdmValue()));
        }
        if(!StringUtil.isBlankOrNull(applicationProperty.getGpValue()) && StringUtil.equals(applicationProperty.getGpValueEncrypted(), YesNoEnum.YES.getValue())){
            applicationProperty.setGpValue(EncryptionUtil.encrypt(applicationProperty.getGpValue()));
        }
    }

    public static void prepareForUpdate(ApplicationProperty applicationProperty, Authentication authentication){
        applicationProperty.setUpdateUser((String)authentication.getPrincipal());
        applicationProperty.setUpdateDate(new Date());
        if(!StringUtil.isBlankOrNull(applicationProperty.getValue()) && StringUtil.equals(applicationProperty.getValueEncrypted(), YesNoEnum.YES.getValue())){
            applicationProperty.setValue(EncryptionUtil.encrypt(applicationProperty.getValue()));
        }
        if(!StringUtil.isBlankOrNull(applicationProperty.getOdmValue()) && StringUtil.equals(applicationProperty.getOdmValueEncrypted(), YesNoEnum.YES.getValue())){
            applicationProperty.setOdmValue(EncryptionUtil.encrypt(applicationProperty.getOdmValue()));
        }
        if(!StringUtil.isBlankOrNull(applicationProperty.getGpValue()) && StringUtil.equals(applicationProperty.getGpValueEncrypted(), YesNoEnum.YES.getValue())){
            applicationProperty.setGpValue(EncryptionUtil.encrypt(applicationProperty.getGpValue()));
        }
    }

    public static ApplicationPropertyDTO prepareForGet(ApplicationPropertyDTO applicationProperty){
        if(!StringUtil.isBlankOrNull(applicationProperty.getValue()) && StringUtil.equals(applicationProperty.getValueEncrypted(), YesNoEnum.YES.getValue())){
            applicationProperty.setValue(EncryptionUtil.decrypt(applicationProperty.getValue()));
        }
        if(!StringUtil.isBlankOrNull(applicationProperty.getOdmValue()) && StringUtil.equals(applicationProperty.getOdmValueEncrypted(), YesNoEnum.YES.getValue())){
            applicationProperty.setOdmValue(EncryptionUtil.decrypt(applicationProperty.getOdmValue()));
        }
        if(!StringUtil.isBlankOrNull(applicationProperty.getGpValue()) && StringUtil.equals(applicationProperty.getGpValueEncrypted(), YesNoEnum.YES.getValue())){
            applicationProperty.setGpValue(EncryptionUtil.decrypt(applicationProperty.getGpValue()));
        }
        return applicationProperty;
    }
    
}
