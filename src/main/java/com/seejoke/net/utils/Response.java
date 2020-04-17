package com.seejoke.net.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * Response
 *
 * @author yangzhongying
 * @date 2018/4/17 19:04
 * @see com.seejoke.net.utils.Response
 **/
public class Response implements Serializable {

    private static final long serialVersionUID = 5431808741731247591L;

    private int statusCode = 200;

    private String statusMessage = "ok";

    private Map<String, String> headers;

    private String encoding;

    private Object body;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Response [statusCode=" + statusCode + ", statusMessage=" + statusMessage + ", headers=" + headers + ", encoding=" + encoding + ", body={}]";
    }

}

