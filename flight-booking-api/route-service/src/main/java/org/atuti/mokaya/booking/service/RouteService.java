package org.atuti.mokaya.booking.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

import org.atuti.mokaya.booking.entity.RouteEntity;
import org.atuti.mokaya.booking.model.Route;
import org.atuti.mokaya.booking.repository.RouteRepository;

import com.fasterxml.jackson.databind.ObjectMapper;


@ApplicationScoped
@Transactional
public class RouteService {
    
    @Inject
    RouteRepository repository;

    @Inject
    AirlineService airlineService;

    @Inject
    AirportService airportService;

    @Inject
    AirplaneService airplaneService;

    public Route findById(Long id){
        return repository.findByIdOptional(id)
        .map(r -> createResponse(r))
        .orElseThrow(() -> new WebApplicationException("Route with id: "+ id + " does not exist", 404));
    }

    private Route createResponse(RouteEntity entity){
        Route route = mapToDomain(entity);
        route.setAirline(airlineService.findById(entity.getAirlineId()));
        route.setSourceAirport(airportService.findById(entity.getSourceAirportId()));
        route.setDestinationAirport(airportService.findById(entity.getDestinationAirportId()));
        if (entity.getAirplaneCode() != null) {
            route.setAirplane(airplaneService.findByIATA(entity.getAirplaneCode()));
        }

        return route;
    }

    public void initData(){
        Pattern pattern = Pattern.compile((","));
        try{

            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("route.dat");
            Stream<String> lines = new BufferedReader(new InputStreamReader(in)).lines();
            lines.forEach(line -> {
                String[] item = pattern.split(line);
                RouteEntity entity = new RouteEntity()
                            .setAirlineICAO(item[0]) 
                            .setAirlineId(Long.parseLong(item[1]))
                            .setSourceAirportIATA(item[2])
                            .setSourceAirportId(Long.parseLong(item[3]))
                            .setDestinationAirportIATA(item[4])
                            .setDestinationAirportId(Long.parseLong(item[5]))
                            .setCodeshare(item[6])
                            .setStops(item[7]);

                            if (item.length > 8){
                                entity.setAirplaneCode(item[8]);
                            }

            repository.persist(entity);
            });
        }catch(Exception ex){
            ex.printStackTrace();
            throw new WebApplicationException(ex.getMessage(), 500);
        }
    }

    // private Long parseItem(String string) {
    //     if (string == ("\\N")){
    //         string = "0";
    //     }
        
    //     return Long.parseLong(string);    
    // }

    public static Route mapToDomain(RouteEntity entity) {
        return new ObjectMapper().convertValue(entity, Route.class);
    }

    public static RouteEntity mapToEntity(Route route){
        return new ObjectMapper().convertValue(route, RouteEntity.class);
    }
}
