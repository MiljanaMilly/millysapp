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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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

    public void save(SkunkDto skunkDto) {
        Skunk skunk = skunkMapper.mapToEntity(skunkDto);

        skunk.setCreatedOn(ZonedDateTime.now());
        skunk.setUpdatedOn(ZonedDateTime.now());
        skunk.setDeleted(Boolean.FALSE);


        if (skunkDto.getChosenDatabase().equals(DatabaseEnum.ALL)) {
            postgresRepository.save(skunk);
            mariaDBRepository.save(skunk);
        } else if (skunkDto.getChosenDatabase().equals(DatabaseEnum.POSTGRES_DB)) {
            postgresRepository.save(skunk);
        } else {
            mariaDBRepository.save(skunk);
        }
    }
}
