package org.atuti.mokaya.passenger.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

import org.atuti.mokaya.passenger.entity.PassengerEntity;
import org.atuti.mokaya.passenger.model.Passenger;
import org.atuti.mokaya.passenger.repository.PassengerRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
@Transactional
public class PassengerService {
    
    @Inject
    PassengerRepository repository;

    @Inject
    BaggageService baggageService;

    public Passenger findById(Long id){
        return repository.findByIdOptional(id)
                    .map(this::createResponse)
                    .orElseThrow(() -> new WebApplicationException("Passenger with an id: "+ id +" does not exist", 404));
    }

    public Passenger create(Passenger passenger){
        PassengerEntity entity = mapToEntity(passenger);
        repository.persistAndFlush(entity);

        return createResponse(entity);
    }

    public Passenger delete(Long id){
        var entity = repository.findByIdOptional(id)
                                .orElseThrow(() -> new WebApplicationException("Passenger with id: "+ id +" does not exist", 404));
        repository.delete(entity);

        return createResponse(entity);
    }

    private Passenger createResponse(PassengerEntity entity){
        Passenger passenger = mapToDomain(entity);
        passenger.setBaggage(baggageService.findById(entity.getBaggageId()));

        return passenger;
    }

    private static Passenger mapToDomain(PassengerEntity entity){
        return new ObjectMapper().convertValue(entity, Passenger.class);
    }

    private static PassengerEntity mapToEntity(Passenger passenger){
        return new ObjectMapper().convertValue(passenger, PassengerEntity.class);
    }
}
