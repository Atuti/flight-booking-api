package org.atuti.mokaya.booking.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.atuti.mokaya.booking.model.Airplane;
import org.atuti.mokaya.booking.service.AirplaneService;
import org.jboss.resteasy.reactive.RestPath;

@Path("/airplanes/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AirplaneResource {
    
    @Inject
    AirplaneService service;

    @GET
    @Path("/{id}")
    public Airplane findById(@RestPath Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/iata/{iata}")
    public Airplane findByIATA(String iata){
        return service.findByIATA(iata);
    }

    @POST
    public Airplane create(Airplane airplane) {
        return service.create(airplane);
    }

    @DELETE
    @Path("/{id}")
    public Airplane delete(long id) {
        return service.delete(id);
    }

    @GET
    @Path("/init")
    public Response initData(){
        service.initData();
        return Response.status(Response.Status.CREATED).build();
    }
}
