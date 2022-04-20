package org.acme.handler.exception;

import org.acme.handler.RespDepartemen;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

public class UnknownDepartemenException extends NotFoundException {
    private static final long serialVersionUID = 1L;

    public UnknownDepartemenException(Response.Status status, RespDepartemen respDepartemen) {
        super(Response.status(status).entity(respDepartemen.toString()).build());
    }
}
