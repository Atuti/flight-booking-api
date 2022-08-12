package org.atuti.mokaya.booking.repository;

import javax.enterprise.context.ApplicationScoped;

import org.atuti.mokaya.booking.entity.RouteEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class RouteRepository implements PanacheRepository<RouteEntity>{
    
}
