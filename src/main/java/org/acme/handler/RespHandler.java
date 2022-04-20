package org.acme.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class RespHandler extends ErrHandler{

    private boolean success;
    private int code;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
