package org.acme.handler;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.model.Departemen;

public class RespDepartemen extends RespHandler{
    private Departemen data;

    public Departemen getData() {
        return data;
    }

    public void setData(Departemen data) {
        this.data = data;
    }

    @Override
    public String toString() {
        Object objJsonWrapper = new JsonWrapper<>(this);
        ObjectMapper om = new ObjectMapper();
        String dept = "";
        try {
            dept =  new ObjectMapper().writeValueAsString(objJsonWrapper);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return dept;
    }
}
