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

import org.atuti.mokaya.booking.model.Country;
import org.atuti.mokaya.booking.service.CountryService;
import org.jboss.resteasy.reactive.RestPath;

@Path("/countries/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CountryResource {
    
    @Inject
    CountryService service;

    @GET
    @Path("/{id}")
    public Country findById(@RestPath Long id) {
        return service.findById(id);
    }
    
    @GET
    @Path("/name/{name}")
    public Country findByName(@RestPath String name) {
        return service.findByName(name);
    }

    @POST
    public Country create(Country country) {
        return service.create(country);
    }

    @DELETE
    @Path("/{id}")
    public Country delete(@RestPath Long id) {
        return service.delete(id);
    }

    @GET
    @Path("/init")
    public Response init() {
        service.initData();
        return Response.status(Response.Status.CREATED).build();
    }
}
