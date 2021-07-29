package com.turkcell.poc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="request_log", schema="amaris")
public class RequestLog implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "logSeqGenerator")
    @SequenceGenerator(name = "logSeqGenerator", sequenceName = "DB_SEQ_NAME", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private String id;
    
    @Column(name = "service", nullable = false, length = 50)
    private String service;
    
    @Lob
    @Column(name="request_body", length =  65535)
    private String requestBody;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestLog", fetch = FetchType.LAZY)
    private List<RequestParameter> requestParameterList;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date date;

    public RequestLog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public List<RequestParameter> getRequestParameterList() {
        return requestParameterList;
    }

    public void setRequestParameterList(List<RequestParameter> requestParameterList) {
        this.requestParameterList = requestParameterList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
}
