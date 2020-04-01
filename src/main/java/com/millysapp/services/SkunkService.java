package com.millysapp.services;

import com.millysapp.dtos.SkunkDto;
import com.millysapp.enums.DatabaseEnum;
import com.millysapp.model.Skunk;
import com.millysapp.repository.mariadb.SkunkMariaDBRepository;
import com.millysapp.repository.postgres.SkunkPostgresRepository;
import com.millysapp.services.dtoMappers.SkunkMapper;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkunkService {

    private final SkunkMariaDBRepository mariaDBRepository;
    private final SkunkPostgresRepository postgresRepository;
    private SkunkMapper skunkMapper;

    @Autowired
    public SkunkService(SkunkMariaDBRepository mariaDBRepository, SkunkPostgresRepository postgresRepository, SkunkMapper skunkMapper) {
        this.mariaDBRepository = mariaDBRepository;
        this.postgresRepository = postgresRepository;
        this.skunkMapper = skunkMapper;
    }

    public List<SkunkDto> findAll(String databaseName) {
        if(databaseName != null) {
            if(databaseName.equals(DatabaseEnum.MARIA_DB.getDisplayName())) {
                return skunkMapper.mapListToDto(mariaDBRepository.findAllByIsDeletedFalse());
            } else {
                return skunkMapper.mapListToDto(postgresRepository.findAllByIsDeletedFalse());
            }
        }
        return null;
    }

//    public List<Skunk> findMAll() {
//        List<Skunk> list =  mariaDBRepository.findAll();
//        System.out.println("Maria Db");
//        for (Skunk skunk : list) {
//            System.out.println(skunk.getName());
//        }
//        return list;
//    }
//
//    public List<Skunk> findPAll() {
//        List<Skunk> list = postgresRepository.findAll();
//        System.out.println("Postgres");
//        for (Skunk skunk : list) {
//            System.out.println(skunk.getName());
//        }
//        return list;
//    }
}
