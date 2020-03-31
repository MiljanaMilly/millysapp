package com.millysapp.services;

import com.millysapp.enums.DatabaseEnum;
import com.millysapp.model.Skunk;
import com.millysapp.repository.mariadb.SkunkMariaDBRepository;
import com.millysapp.repository.postgres.SkunkPostgresRepository;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkunkService {

    private final SkunkMariaDBRepository mariaDBRepository;
    private final SkunkPostgresRepository postgresRepository;

    @Autowired
    public SkunkService(SkunkMariaDBRepository mariaDBRepository, SkunkPostgresRepository postgresRepository) {
        this.mariaDBRepository = mariaDBRepository;
        this.postgresRepository = postgresRepository;
    }

    public List<Skunk> findAll(String databaseName) {
        if(databaseName.equals(DatabaseEnum.POSTGRES_DB.getDisplayName())) {
            return postgresRepository.findAll();
        } else {
            return mariaDBRepository.findAll();
        }
    }

    public List<Skunk> findMAll() {
        List<Skunk> list =  mariaDBRepository.findAll();
        System.out.println("Maria Db");
        for (Skunk skunk : list) {
            System.out.println(skunk.getName());
        }
        return list;
    }

    public List<Skunk> findPAll() {
        List<Skunk> list = postgresRepository.findAll();
        System.out.println("Postgres");
        for (Skunk skunk : list) {
            System.out.println(skunk.getName());
        }
        return list;
    }
}
