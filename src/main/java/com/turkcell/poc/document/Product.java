package com.turkcell.poc.document;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Product")
public class Product implements Serializable {
    
    @Id
    private Long id;
    
    private String mobileNumber;
    
    private String username;
    
    private String line;
    
    private String lineType;
    
    private String paymentType;
    
    private Integer shortNumber;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getShortNumber() {
        return shortNumber;
    }

    public void setShortNumber(Integer shortNumber) {
        this.shortNumber = shortNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", mobileNumber=" + mobileNumber + ", username=" + username + ", line=" + line + ", lineType=" + lineType + ", paymentType=" + paymentType + ", shortNumber=" + shortNumber + '}';
    }

  
}
