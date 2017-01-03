package com.ezio.bilibili.network;

/**
 * Author：Ezio on 2016/12/23.
 */
public class HttpResult<T> {

    /**
     * code : 0
     * message : ok
     */

    private int code;
    private String message;
    private T data;
    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
