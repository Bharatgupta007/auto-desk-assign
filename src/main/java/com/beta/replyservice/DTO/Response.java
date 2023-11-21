package com.beta.replyservice.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Response {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String data;

    public Response(String message, String data) {
        this.message = message;
        this.data = data;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
