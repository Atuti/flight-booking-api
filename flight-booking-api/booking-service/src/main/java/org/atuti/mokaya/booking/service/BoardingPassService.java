package org.atuti.mokaya.booking.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

import org.atuti.mokaya.booking.entity.BoardingPassEntity;
import org.atuti.mokaya.booking.model.BoardingPass;
import org.atuti.mokaya.booking.repository.BoardingPassRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
@Transactional
public class BoardingPassService {
    
    @Inject
    BoardingPassRepository repository;

    @Inject BookingService bookingService;

    public Set<BoardingPass> findAll(){
        return repository.findAll().stream()
        .map(this::createResponse)
        .collect(Collectors.toSet());
    }

    public BoardingPass findById(Long id){
        return repository.findByIdOptional(id)
        .map(this::createResponse)
        .orElseThrow(() -> new WebApplicationException("A boarding pass with id: " + id +" does not exist", 404));
    }

    public BoardingPass create(BoardingPass boardingPass){
        BoardingPassEntity entity = mapToEntity(boardingPass);
        repository.persistAndFlush(entity);

        return createResponse(entity);
    }

    public BoardingPass delete(Long id){
        var entity = repository.findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("A boarding pass with the id: "+ id +" does not exist", 404));

        repository.delete(entity);
        return mapToDomain(entity);
    }

    public BoardingPass createResponse(BoardingPassEntity entity){
        BoardingPass boardingPass = mapToDomain(entity);
        boardingPass.setBooking(bookingService.findById(entity.getBookingId()));

        return boardingPass;
    }

    public static BoardingPass mapToDomain(BoardingPassEntity entity){
        return new ObjectMapper().convertValue(entity, BoardingPass.class);
    }

    public static BoardingPassEntity mapToEntity(BoardingPass boardingPass){
        return new ObjectMapper().convertValue(boardingPass, BoardingPassEntity.class);
    }
}
