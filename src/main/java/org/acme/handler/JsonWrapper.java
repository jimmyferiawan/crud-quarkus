package org.acme.handler;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class JsonWrapper<T> {
    @JsonUnwrapped
    private T inner;

    public JsonWrapper(T inner) {
        this.inner = inner;
    }

    public T getInner() {
        return inner;
    }

}
