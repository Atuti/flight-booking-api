package org.atuti.mokaya.passenger.resource;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.atuti.mokaya.passenger.model.Baggage;
import org.atuti.mokaya.passenger.service.BaggageService;
import org.jboss.resteasy.reactive.RestPath;

@Path("/baggage/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BaggageResource {
    
    @Inject
    BaggageService service;

    @GET
    public Set<Baggage> findAll(){
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Baggage findById(@RestPath Long id){
        return service.findById(id);
    }

    @POST
    public Baggage create(Baggage baggage){
        return service.create(baggage);
    }

    @DELETE
    @Path("/{id}")
    public Baggage delete(@RestPath Long id){
        return service.delete(id);
    }
}
