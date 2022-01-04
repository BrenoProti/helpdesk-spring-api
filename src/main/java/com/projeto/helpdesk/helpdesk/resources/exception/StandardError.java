package com.projeto.helpdesk.helpdesk.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long timestramp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public void setTimestramp(Long timestramp) {
        this.timestramp = timestramp;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getTimestramp() {
        return timestramp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public StandardError(Long timestramp, Integer status, String error, String message, String path) {
        this.timestramp = timestramp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public StandardError() {
       super();
    }
}
