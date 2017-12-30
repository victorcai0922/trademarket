package com.start.demo.bean;

public class HttpResult {

    private Integer code;

    private String body;

    public HttpResult(Integer code, String s, String body){
        this.body = body;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
