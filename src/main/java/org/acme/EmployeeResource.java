package org.acme;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.handler.BaseResp;
import org.acme.handler.RespEmployee;
import org.acme.handler.RespEmployeeList;
import org.acme.model.Emp;
import org.acme.model.Employee;
import org.acme.repository.EmployeeRepository;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/employee")
@ApplicationScoped
public class EmployeeResource {
    private static final Logger LOG = Logger.getLogger(EmployeeResource.class);
    private ObjectMapper mapper;
    @Inject
    EmployeeRepository employeeRepository;

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public Response listEmployee() {
        LOG.info("@GET /api/employee");
        int httCode;
        RespEmployeeList<Employee> resp = new RespEmployeeList<>();
        try {
            List<Employee> listEmp = employeeRepository.listAll();
            httCode=200;
            resp.setData(listEmp);
            resp.setCode(httCode);
            resp.setSuccess(true);
        } catch (Exception e) {
            httCode = 400;
            resp.setError_code(httCode);
            resp.setError_description(e.getCause().getCause().toString());
        }

        System.out.println(resp.toString());
        return Response.status(httCode).entity(resp.toString()).build();
    }

    @GET
    @Path("{me}")
    @Produces("application/json")
    public Response employeeById(@PathParam int me) {

        LOG.info("@GET /api/employee/{int}");
        Employee employee = employeeRepository.find("memployeepk", me).firstResult();
        BaseResp<Employee> respEmpById = new BaseResp<>();
        int httpCode;
        if(employee != null) {
            httpCode = 200;
            respEmpById.setCode(httpCode);
            respEmpById.setSuccess(true);
            respEmpById.setData(employee);
        } else {
            httpCode=404;
            respEmpById.setError_code(httpCode);
            respEmpById.setError_description("id: " + me + " Not found!");
        }
        System.out.println(respEmpById.toString());
        return Response.ok().entity(respEmpById.toString()).build();
    }

    @PUT
    @Path("{me}")
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional
    public Response employeeUpdate(@PathParam("me") int me, Emp emp) {
        LOG.info("@PUT /api/employee/{int}");
        int httpCode;
        RespEmployee respEmployeeUpdate = new RespEmployee();
        Employee employee = employeeRepository.find("memployeepk", me).firstResult();
        if(employee != null) {

            try {
                employeeRepository.update("mdepartemenfk=?1, employeename=?2, npp=?3, address=?4, join_at=?5 where memployeepk=?6", emp.getId_departemen(), emp.getNama(), emp.getNpp(), emp.getAlamat(), emp.getTgl_gabung(), me);
                httpCode = 200;
                employee.setId_departemen(emp.getId_departemen());
                employee.setNama(emp.getNama());
                employee.setNpp(emp.getNpp());
                employee.setAlamat(emp.getAlamat());
                employee.setTgl_gabung(emp.getTgl_gabung());
                System.out.println("tgl gabung :" + employee.getTgl_gabung());
                respEmployeeUpdate.setEmployee(employee);
                respEmployeeUpdate.setCode(httpCode);
                respEmployeeUpdate.setSuccess(true);

            } catch (Exception e) {
                httpCode = 400;
                respEmployeeUpdate.setError_code(httpCode);
                respEmployeeUpdate.setError_description(e.getCause().getCause().toString());
            }
        } else {
            httpCode = 404;
            respEmployeeUpdate.setError_code(httpCode);
            respEmployeeUpdate.setError_description("Data Not found");
        }
        System.out.println(respEmployeeUpdate.toString());
        return Response.status(httpCode).entity(respEmployeeUpdate.toString()).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional
    public Response employeeCreate(Emp emp) {
        LOG.info("@POST /api/employee");
        int httpCode;
        RespEmployee respEmployee = new RespEmployee();
        Employee employee = new Employee();

        if(emp != null) {
            employee.setId_departemen(emp.getId_departemen());
            employee.setNama(emp.getNama());
            employee.setNpp(emp.getNpp());
            employee.setAlamat(emp.getAlamat());
            employee.setTgl_gabung(emp.getTgl_gabung());

            try{
                httpCode = 200;
                employeeRepository.persist(employee);
                respEmployee.setEmployee(employee);
                respEmployee.setCode(httpCode);
                respEmployee.setSuccess(true);

            } catch (Exception e) {
                httpCode = 400;
                respEmployee.setError_code(httpCode);
                respEmployee.setError_description(e.getCause().getCause().toString());

            }
        } else {
            httpCode = Response.Status.NOT_MODIFIED.getStatusCode();
            respEmployee.setError_description("Field tidak boleh kosong");
            respEmployee.setError_code(httpCode);
        }
        System.out.println(respEmployee.toString());
        return Response.status(httpCode).entity(respEmployee.toString()).build();
    }

    @DELETE
    @Path("{me}")
    @Transactional
    @Produces("application/json")
    public Response employeeDelete(@PathParam("me") int me) {
        LOG.info("@DELETE /api/employee/{int}");
        RespEmployee respEmployeeDel = new RespEmployee();
        Employee employee = employeeRepository.find("memployeepk", me).firstResult();
        int httpCode = 0;

        if(employee != null) {
            try {
                employeeRepository.delete("memployeepk", me);
                httpCode=200;
                respEmployeeDel.setCode(httpCode);
                respEmployeeDel.setSuccess(true);
                respEmployeeDel.setEmployee(employee);
            } catch (Exception e) {
                httpCode=400;
                respEmployeeDel.setError_code(httpCode);
                respEmployeeDel.setError_description(e.getCause().getCause().toString());
            }
        } else {
            httpCode=404;
            respEmployeeDel.setError_code(httpCode);
            respEmployeeDel.setError_description("Data " +  me + " tidak ditemukan!");
        }
        System.out.println(respEmployeeDel.toString());
        return Response.status(httpCode).entity(respEmployeeDel.toString()).build();
    }

}
