package org.acme.handler;

import java.util.List;

public class  RespDepartemenList<Departemen> extends RespHandler{
    private List<Departemen> data;

    public List<Departemen> getData() {
        return data;
    }

    public void setData(List<Departemen> data) {
        this.data = data;
    }


}
