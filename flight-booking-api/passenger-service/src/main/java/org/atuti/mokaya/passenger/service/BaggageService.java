package org.atuti.mokaya.passenger.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

import org.atuti.mokaya.passenger.entity.BaggageEntity;
import org.atuti.mokaya.passenger.model.Baggage;
import org.atuti.mokaya.passenger.repository.BaggageRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
@Transactional
public class BaggageService {
    
    @Inject
    BaggageRepository repository;

    public Set<Baggage> findAll(){
        return repository.findAll().stream()
        .map(BaggageService::mapToDomain)
        .collect(Collectors.toSet());
    }

    public Baggage findById(Long id) {
        return repository.findByIdOptional(id)
        .map(BaggageService::mapToDomain)
        .orElseThrow(() -> new WebApplicationException("Baggage with id: "+ id +" does not exist", 404));
    }

    public Baggage create(Baggage baggage){
        BaggageEntity entity = mapToEntity(baggage);
        repository.persistAndFlush(entity);

        return mapToDomain(entity);
    }

    public Baggage delete(Long id){
        var entity = repository.findByIdOptional(id)
                                .orElseThrow(() -> new WebApplicationException("Baggage with id: "+ id +"does not exist", 404));
        repository.delete(entity);

        return mapToDomain(entity);
    }

    private static Baggage mapToDomain(BaggageEntity entity){
        return new ObjectMapper().convertValue(entity, Baggage.class);
    }

    private static BaggageEntity mapToEntity(Baggage baggage){
        return new ObjectMapper().convertValue(baggage, BaggageEntity.class);
    }
}
