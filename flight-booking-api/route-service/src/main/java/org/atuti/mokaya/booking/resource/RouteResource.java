package org.atuti.mokaya.booking.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.atuti.mokaya.booking.model.Route;
import org.atuti.mokaya.booking.service.RouteService;
import org.jboss.resteasy.reactive.RestPath;

@Path("/routes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RouteResource {

    @Inject
    RouteService service;

    @GET
    @Path("/{id}")
    public Route findById(@RestPath Long id){
        return service.findById(id);
    }

    @GET
    @Path("init")
    public Response initData(){
        service.initData();
        return Response.status(Response.Status.CREATED).build();
    }
    
}