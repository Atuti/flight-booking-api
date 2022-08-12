package org.atuti.mokaya.booking.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

import org.atuti.mokaya.booking.entity.FlightEntity;
import org.atuti.mokaya.booking.model.Flight;
import org.atuti.mokaya.booking.repository.FlightRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
@Transactional
public class FlightService {
    
    @Inject
    FlightRepository repository;

    public Set<Flight> findALl(){
        return repository.findAll().stream()
        .map(FlightService::mapToDomain)
        .collect(Collectors.toSet());
    }

    public Flight findById(Long id ){
        return repository.findByIdOptional(id)
        .map(FlightService::mapToDomain)
        .orElseThrow(() -> new WebApplicationException("Flight with id: "+ id +" does not exist", 404));
    }

    public Flight findByRouteId(Long id){
        return repository.find("routeid", id).firstResultOptional()
        .map(FlightService::mapToDomain)
        .orElseThrow(() -> new WebApplicationException("Flight with route id: "+ id +" does not exist", 404));
    }

    public Flight create(Flight flight){
        FlightEntity entity = mapToEntity(flight);
        repository.persistAndFlush(entity);

        return mapToDomain(entity);
    }

    public Flight delete(Long id){
        var entity = repository.findByIdOptional(id)
        .orElseThrow(() -> new WebApplicationException("Flight with id: "+ id +" does not exist", 404));

        repository.delete(entity);

        return mapToDomain(entity);
    }

    private static Flight mapToDomain(FlightEntity entity){
        return new ObjectMapper().convertValue(entity, Flight.class);
    }

    private static FlightEntity mapToEntity(Flight flight){
        return new ObjectMapper().convertValue(flight, FlightEntity.class);
    }
}
