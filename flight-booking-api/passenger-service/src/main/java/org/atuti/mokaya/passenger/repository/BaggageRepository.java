package org.atuti.mokaya.passenger.repository;

import javax.enterprise.context.ApplicationScoped;

import org.atuti.mokaya.passenger.entity.BaggageEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class BaggageRepository implements PanacheRepository<BaggageEntity>{
    
}
