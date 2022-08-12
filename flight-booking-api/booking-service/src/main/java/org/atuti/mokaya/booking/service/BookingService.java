package org.atuti.mokaya.booking.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

import org.atuti.mokaya.booking.entity.BookingEntity;
import org.atuti.mokaya.booking.model.Booking;
import org.atuti.mokaya.booking.repository.BookingRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
@Transactional
public class BookingService {
    
    @Inject
    BookingRepository repository;

    @Inject
    FlightService flightService;

    public Set<Booking> findAll(){
        return repository.findAll().stream()
        .map(this::createResponse)
        .collect(Collectors.toSet());
    }
     
    public Booking findById(Long id){
        return repository.findByIdOptional(id)
        .map(this::createResponse)
        .orElseThrow(() -> new WebApplicationException("A booking with the id: "+ id + " does not exist", 404));
    }

    public Booking create(Booking booking){
        BookingEntity entity = mapToEntity(booking);
        repository.persistAndFlush(entity);

        return createResponse(entity);
    }

    public Booking delete(Long id){
        var entity = repository.findByIdOptional(id)
        .orElseThrow(() -> new WebApplicationException("A booking with the id: "+ id + "does not exist", 404));

        repository.delete(entity);
        
        return createResponse(entity);
    }
    
    public Booking createResponse(BookingEntity entity){
        Booking booking = mapToDomain(entity);
        booking.setFlight(flightService.findById(entity.getFlightId()));
        return booking;
    }

    public static Booking mapToDomain(BookingEntity entity){
        return new ObjectMapper().convertValue(entity, Booking.class);
    }

    public static BookingEntity mapToEntity(Booking booking){
        return new ObjectMapper().convertValue(booking, BookingEntity.class);
    }
}
