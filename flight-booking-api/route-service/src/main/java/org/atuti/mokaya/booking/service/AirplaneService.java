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

import org.atuti.mokaya.booking.entity.AirplaneEntity;
import org.atuti.mokaya.booking.model.Airplane;
import org.atuti.mokaya.booking.repository.AirplaneRepoitory;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
@Transactional
public class AirplaneService {
    
    @Inject
    AirplaneRepoitory repository;

    public Airplane findById(Long id) {
        return repository.findByIdOptional(id)
        .map(AirplaneService::mapToDomain)
        .orElseThrow(() -> new WebApplicationException("Airplane with id: "+ id + "not found", 404));
    }

    public Airplane findByIATA(String iata) {
        return repository.find("iatacode", iata).firstResultOptional()
        .map(AirplaneService::mapToDomain)
        .orElse(new Airplane());
        // .orElseThrow(() -> new WebApplicationException("Airplane with iataCode: "+ iata + "was not found", 404));
    }

    public Airplane create(Airplane airplane){
        AirplaneEntity entity = mapToEntity(airplane);
        repository.persistAndFlush(entity);
        return mapToDomain(entity);
    }

    public Airplane delete(Long id) {
        var entity = repository.findByIdOptional(id)
        .orElseThrow(() -> new WebApplicationException("Airplane with id: "+ id +"not found", 404));
        repository.delete(entity);

        return mapToDomain(entity);
    }

    public void initData(){
        Pattern pattern = Pattern.compile((","));
        try{
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("airplane.dat");
            Stream<String> lines = new BufferedReader(new InputStreamReader(in)).lines();
            lines.forEach(line -> {
                String[] item = pattern.split(line);
                AirplaneEntity airplaneEntity = new AirplaneEntity()
                                .setName(item[0])
                                .setIataCode(item[1]);
                                if (item.length > 2){
                                    airplaneEntity.setIcaoCode(item[2]);
                                }

                                repository.persist(airplaneEntity);
            });
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static Airplane mapToDomain(AirplaneEntity entity) {
        return new ObjectMapper().convertValue(entity, Airplane.class);
    }

    public static AirplaneEntity mapToEntity(Airplane airplane){
        return new ObjectMapper().convertValue(airplane, AirplaneEntity.class);
    }
}
