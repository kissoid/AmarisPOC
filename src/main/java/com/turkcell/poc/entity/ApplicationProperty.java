package com.turkcell.poc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(schema = "amaris", name = "application_property",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"key"})
        })
public class ApplicationProperty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "applicationPropertySeqGenerator")
    @SequenceGenerator(name = "applicationPropertySeqGenerator", sequenceName = "DB_SEQ_NAME", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "key", nullable = false, length = 50)
    private String key;

    @Column(name = "value", nullable = false, length = 50)
    private String value;

    @Column(name = "value_encrypted", nullable = false, length = 1)
    private String valueEncrypted;

    @Column(name = "odm_value", length = 50)
    private String odmValue;

    @Column(name = "odm_value_encrypted", length = 1)
    private String odmValueEncrypted;

    @Column(name = "gp_value", length = 50)
    private String gpValue;

    @Column(name = "gp_value_encrypted", length = 1)
    private String gpValueEncrypted;

    @Column(name = "create_user", nullable = false, length = 20)
    private String createUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "update_user", length = 20)
    private String updateUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date updateDate;

    @Column(name = "description", length = 255)
    private String description;

    public ApplicationProperty() {
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
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final ApplicationProperty other = (ApplicationProperty) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ApplicationProperty{" + "id=" + id + ", key=" + key + ", value=" + value + ", valueEncrypted=" + valueEncrypted + ", odm=" + odmValue + ", odmValueEncrypted=" + odmValueEncrypted + ", gp=" + gpValue + ", gpValueEncrypted=" + gpValueEncrypted + ", createUser=" + createUser + ", createDate=" + createDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + '}';
    }

}
