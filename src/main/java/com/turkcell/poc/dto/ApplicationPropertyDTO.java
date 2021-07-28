package com.turkcell.poc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ApplicationPropertyDTO implements Serializable {

    private Long id;

    private String key;

    private String value;
    
    private String valueEncrypted;

    private String odmValue;
    
    private String odmValueEncrypted;    

    private String gpValue;
    
    private String gpValueEncrypted;    
    
    private String createUser;

    private Date createDate;
    
    private String updateUser;
    
    private Date updateDate;
    
    private String description;

    public ApplicationPropertyDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueEncrypted() {
        return valueEncrypted;
    }

    public void setValueEncrypted(String valueEncrypted) {
        this.valueEncrypted = valueEncrypted;
    }

    public String getOdmValue() {
        return odmValue;
    }

    public void setOdmValue(String odmValue) {
        this.odmValue = odmValue;
    }

    public String getOdmValueEncrypted() {
        return odmValueEncrypted;
    }

    public void setOdmValueEncrypted(String odmValueEncrypted) {
        this.odmValueEncrypted = odmValueEncrypted;
    }

    public String getGpValue() {
        return gpValue;
    }

    public void setGpValue(String gpValue) {
        this.gpValue = gpValue;
    }

    public String getGpValueEncrypted() {
        return gpValueEncrypted;
    }

    public void setGpValueEncrypted(String gpValueEncrypted) {
        this.gpValueEncrypted = gpValueEncrypted;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.key);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ApplicationPropertyDTO other = (ApplicationPropertyDTO) obj;
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ApplicationPropertyDTO{" + "id=" + id + ", key=" + key + ", value=" + value + ", valueEncrypted=" + valueEncrypted + ", odm=" + odmValue + ", odmValueEncrypted=" + odmValueEncrypted + ", gp=" + gpValue + ", gpValueEncrypted=" + gpValueEncrypted + ", createUser=" + createUser + ", createDate=" + createDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + '}';
    }


}
