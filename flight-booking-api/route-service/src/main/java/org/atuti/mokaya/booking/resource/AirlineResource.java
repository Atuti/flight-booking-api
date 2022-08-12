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

import org.atuti.mokaya.booking.model.Airline;
import org.atuti.mokaya.booking.service.AirlineService;
import org.jboss.resteasy.reactive.RestPath;

@Path("airlines/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AirlineResource {

    @Inject
    AirlineService service;

    @GET
    @Path("/{id}")
    public Airline findById(@RestPath Long id) {
        return service.findById(id);
    }

    @POST
    public Airline create(Airline airline){
        return service.create(airline);
    }

    @DELETE
    @Path("/{id}")
    public Airline delete(@RestPath Long id) {
        return service.delete(id);
    }

    @GET
    @Path("/init")
    public Response initData(){
        service.initData();
        return Response.status(Response.Status.CREATED).build();
    }
}
