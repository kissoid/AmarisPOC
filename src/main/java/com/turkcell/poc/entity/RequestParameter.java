package com.turkcell.poc.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class RequestParameter {

    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "value", nullable = false, length = 50)
    private String value;
    @JoinColumn(name = "request_log_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private RequestLog requestLog;

    public RequestParameter() {
    }

    public RequestParameter(String name, String[] value) {
        this.name = name;
        if (value != null && value.length > 0) {
            this.value = value[0];
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RequestLog getRequestLog() {
        return requestLog;
    }

    public void setRequestLog(RequestLog requestLog) {
        this.requestLog = requestLog;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.name);
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
        final RequestParameter other = (RequestParameter) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RequestParameter{" + "name=" + name + ", value=" + value + '}';
    }

}
