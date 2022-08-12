package org.atuti.mokaya.booking.repository;

import javax.enterprise.context.ApplicationScoped;

import org.atuti.mokaya.booking.entity.AirplaneEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class AirplaneRepoitory implements PanacheRepository<AirplaneEntity>{
    
}
