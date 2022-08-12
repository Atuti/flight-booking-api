package org.atuti.mokaya.booking.resource;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.atuti.mokaya.booking.model.Flight;
import org.atuti.mokaya.booking.service.FlightService;
import org.jboss.resteasy.reactive.RestPath;

@Path("/flights/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlightResource {
    
    @Inject
    FlightService service;

    @GET
    public Set<Flight> findALlFlights(){
        return service.findALl();
    }

    @GET
    @Path("/{id}")
    public Flight findById(Long id){
        return service.findById(id);
    }

    @POST
    public Flight create(Flight flight){
        return service.create(flight);
    }

    @DELETE
    @Path("/{id}")
    public Flight deleteFLight(@RestPath Long id){
        return service.delete(id);
    }

}
