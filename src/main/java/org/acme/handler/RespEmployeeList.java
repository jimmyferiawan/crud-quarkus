package org.acme.handler;

import java.util.List;

public class RespEmployeeList<T> extends RespHandler {
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
