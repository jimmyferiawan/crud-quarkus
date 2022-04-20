package org.acme.handler;

public class BaseResp<T> extends RespHandler{
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
