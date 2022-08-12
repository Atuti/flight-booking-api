package org.atuti.mokaya.booking.repository;

import javax.enterprise.context.ApplicationScoped;

import org.atuti.mokaya.booking.entity.AirportEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class AirportRepository implements PanacheRepository<AirportEntity>{
    
}
