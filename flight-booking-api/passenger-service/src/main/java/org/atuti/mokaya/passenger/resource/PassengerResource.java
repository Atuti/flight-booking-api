package org.atuti.mokaya.passenger.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.atuti.mokaya.passenger.model.Passenger;
import org.atuti.mokaya.passenger.service.PassengerService;
import org.jboss.resteasy.reactive.RestPath;

@Path("/passenger/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PassengerResource {

    @Inject
    PassengerService service;

    @GET
    @Path("/{id}")
    public Passenger findById(@RestPath Long id){
        return service.findById(id);
    }

    @POST
    public Passenger create(Passenger passenger){
        return service.create(passenger);
    }

    @DELETE
    @Path("/{id}")
    public Passenger delete(@RestPath Long id){
        return service.delete(id);
    }

}