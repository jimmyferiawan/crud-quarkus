package org.acme;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.quarkus.logging.Log;
import org.acme.handler.RespDepartemen;
import org.acme.handler.RespDepartemenList;
import org.acme.handler.exception.UnknownDepartemenException;
import org.acme.model.Departemen;
import org.acme.model.Dept;
import org.acme.repository.DepartemenRepository;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/departemen")
@ApplicationScoped
public class DepartemenResource {
    private static final Logger LOG = Logger.getLogger(DepartemenResource.class);

    @Inject
    DepartemenRepository departemenRepository;

    @GET
    @Produces("application/json")
    public RespDepartemenList<Departemen> listDepartemen() {
        Log.info("@GET /api/departemen");
        RespDepartemenList<Departemen> resp = new RespDepartemenList<>();
        int httpCode;
        try {
            List<Departemen> listDept = departemenRepository.listAll();
            httpCode = 200;
            resp.setData(listDept);
            resp.setSuccess(true);
            resp.setCode(httpCode);
        } catch (Exception e){
            httpCode = 400;
            resp.setError_code(httpCode);
            resp.setError_description(e.getCause().getCause().toString());
        }
        System.out.println(resp.toString());
        return resp;
    }

    @GET
    @Path("{me}")
    @Produces("application/json")
    public RespDepartemen departemenById(@PathParam String me) {

        Log.info("@GET /api/departemen{int}");
        int deptId = Integer.valueOf(me);
        RespDepartemen resp = new RespDepartemen();
        Departemen dept = departemenRepository.find("mdepartemenpk", deptId).firstResult();
        int httpCode;

        if(dept != null) {
            httpCode=200;
            resp.setData(dept);
            resp.setCode(httpCode);
            resp.setSuccess(true);
        } else {

            httpCode=404;
            resp.setError_code(httpCode);
            resp.setError_description("No data found!");
            throw new UnknownDepartemenException(Response.Status.NOT_FOUND, resp);
        }
        System.out.println(resp.toString());
        return resp;
//        return Response.status(httpCode).entity(resp).build();

    }


    @PUT
    @Path("{me}")
    @Produces("application/json")
    @Consumes("application/json")
    @Transactional
    public Response departemenUpdate(@PathParam String me, Dept dept) throws JsonProcessingException {
        Log.info("@PUT /api/departemen/{int}");
        int deptId = Integer.valueOf(me);
        int httpCode = 0;
        RespDepartemen respDepartemenUpdate = new RespDepartemen();
        Departemen departemen = departemenRepository.find("mdepartemenpk" ,deptId).firstResult();

        if( departemen != null && dept != null & dept.getDeptCode() != null && dept.getDeptName() != null) {
            LOG.info("dept.getDeptName(): " + dept.getDeptName() + " dept.getDeptCode(): " + dept.getDeptCode());
            departemen.setmDepartemenPk(deptId);
            departemen.setDeptName(dept.getDeptName().trim());
            departemen.setDeptCode(dept.getDeptCode().trim());
            try {
                departemenRepository.update("deptcode=?1, deptname=?2 WHERE mdepartemenpk=?3", departemen.getDeptCode(), departemen.getDeptName(), deptId);
                respDepartemenUpdate.setCode(httpCode);
                respDepartemenUpdate.setSuccess(true);
                respDepartemenUpdate.setData(departemen);
                httpCode = 200;
            } catch (Exception e) {
                httpCode = 404;
                respDepartemenUpdate.setError_code(404);
                respDepartemenUpdate.setError_description(e.getCause().getCause().toString());
            }
        } else {
            httpCode = 404;
            respDepartemenUpdate.setError_code(httpCode);
            respDepartemenUpdate.setError_description("Data with Id " + deptId + " Not Found");
        }

        System.out.println(respDepartemenUpdate.toString());
        return Response.status(httpCode).entity(respDepartemenUpdate.toString()).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Transactional
    public Response departemenCreate(Dept dept) {
        Log.info("@POST /api/departemen");
        Departemen departemen = new Departemen();
        RespDepartemen respDepartemenCreate = new RespDepartemen();
        departemen.setDeptCode(dept.getDeptCode());
        departemen.setDeptName(dept.getDeptName());
        int httpCode = 0;

        if(dept != null && dept.getDeptName() != null && dept.getDeptCode() != null) {
            try {
                departemenRepository.persist(departemen);
                httpCode = Response.Status.CREATED.getStatusCode();
                respDepartemenCreate.setCode(httpCode);
                respDepartemenCreate.setSuccess(true);
                respDepartemenCreate.setData(departemen);

            } catch (Exception e) {
                httpCode = 400;
                respDepartemenCreate.setError_code(httpCode);
                respDepartemenCreate.setError_description(e.getCause().getCause().toString());
            }
        } else {
            httpCode = 404;
            respDepartemenCreate.setError_code(httpCode);
            respDepartemenCreate.setError_description("error data tidak boleh kosong");
        }

        System.out.println(respDepartemenCreate.toString());
        return Response.status(httpCode).entity(respDepartemenCreate.toString()).build();
    }

    @DELETE
    @Path("{me}")
    @Produces("application/json")
    @Transactional
    public Response departemenDelete( @PathParam String me) {
        Log.info("@DELETE /api/departemen");
        Departemen departemen;
        RespDepartemen respDepartemenDelete = new RespDepartemen();
        int deptId = Integer.valueOf(me);
        int httpCode = 0;

        departemen = departemenRepository.find("mdepartemenpk", deptId).firstResult();
        if( departemen != null) {
            try {
                departemenRepository.delete(departemen);
                httpCode = 200;
                respDepartemenDelete.setSuccess(true);
                respDepartemenDelete.setCode(httpCode);
                respDepartemenDelete.setData(departemen);
            } catch (Exception e) {
                httpCode = 500;
                respDepartemenDelete.setError_code(httpCode);
                respDepartemenDelete.setError_description(e.getCause().getCause().toString());
            }
        } else {
            httpCode = 404;
            respDepartemenDelete.setError_description("something wrong! cannot find data with id " + deptId);
            respDepartemenDelete.setError_code(httpCode);
        }
        System.out.println(departemenRepository.toString());
        return Response.status(httpCode).entity(respDepartemenDelete.toString()).build();

    }
}
