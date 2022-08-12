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

import org.atuti.mokaya.booking.model.BoardingPass;
import org.atuti.mokaya.booking.service.BoardingPassService;

@Path("/boarding-passes/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoardingPassResource {

    @Inject
    BoardingPassService service;

    @GET
    public Set<BoardingPass> findAll(){
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public BoardingPass findById(Long id){
        return service.findById(id);
    }

    @POST
    public BoardingPass create(BoardingPass boardingPass ){
        return service.create(boardingPass);
    }

    @DELETE
    public BoardingPass delete(Long id){
        return service.delete(id);
    }
    
}
