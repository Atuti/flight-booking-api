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

import org.atuti.mokaya.booking.model.Booking;
import org.atuti.mokaya.booking.service.BookingService;
import org.jboss.resteasy.reactive.RestPath;

@Path("/bookings/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {

    @Inject
    BookingService service;

    @GET
    public Set<Booking> findAll(){
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Booking findById(@RestPath Long id){
        return service.findById(id);
    }

    @POST
    public Booking create(Booking booking){
        return service.create(booking);
    }

    @DELETE
    @Path("/{id}")
    public Booking delete(Long id){
        return service.delete(id);
    }
}