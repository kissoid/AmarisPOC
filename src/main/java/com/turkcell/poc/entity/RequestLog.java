package com.turkcell.poc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Id;

public class RequestLog implements Serializable {
    
    @Id
    private String id;
    
    private String service;
    
    private String requestBody;
    
    private List<RequestParameter> requestParameterList;
    
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
