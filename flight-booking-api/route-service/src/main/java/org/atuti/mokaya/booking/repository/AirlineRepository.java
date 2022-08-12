package org.atuti.mokaya.booking.repository;

import javax.enterprise.context.ApplicationScoped;

import org.atuti.mokaya.booking.entity.AirlineEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class AirlineRepository implements PanacheRepository<AirlineEntity>{
    
}
