package com.millysapp.services;

import com.millysapp.dtos.SkunkDto;
import com.millysapp.enums.DatabaseEnum;
import com.millysapp.model.Skunk;
import com.millysapp.repository.mariadb.SkunkMariaDBRepository;
import com.millysapp.repository.postgres.SkunkPostgresRepository;
import com.millysapp.services.dtoMappers.SkunkMapper;
import javassist.NotFoundException;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@SuppressWarnings("all")
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

    public SkunkDto findSkunkById(UUID skunkId, String datasource) throws NotFoundException {
        Optional<Skunk> skunk;
        if(datasource.equals(DatabaseEnum.POSTGRES_DB.getDisplayName())) {
            skunk = postgresRepository.findBySkunkIdAndIsDeletedFalse(skunkId);
        } else {
            skunk = mariaDBRepository.findBySkunkIdAndIsDeletedFalse(skunkId);
        }
        if(skunk.isPresent()){
            return skunkMapper.mapToDto(skunk.get());
        }
        throw new NotFoundException("No such skunk");
    }

    public void save(SkunkDto skunkDto) {
        Skunk skunk = skunkMapper.mapToEntity(skunkDto);
        skunk.setSkunkId(UUID.randomUUID());
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

    public void edit(SkunkDto skunkDto) {

        if(skunkDto.getChosenDatabase().equals(DatabaseEnum.POSTGRES_DB)) {
            editAtPgDb(skunkDto);
        } else if (skunkDto.getChosenDatabase().equals(DatabaseEnum.MARIA_DB)) {
            editAtMdb(skunkDto);
        } else {
            editAtMdb(skunkDto);
            editAtPgDb(skunkDto);
        }
    }

    public void editAtPgDb(SkunkDto skunkDto) {
        Optional<Skunk> optionalSkunk = postgresRepository.findBySkunkIdAndIsDeletedFalse(skunkDto.getSkunkId());
        if(!optionalSkunk.isPresent()) {
            Skunk skunk = skunkMapper.mapToEntity(skunkDto);
            skunk.setSkunkId(skunkDto.getSkunkId());
            skunk.setCreatedOn(ZonedDateTime.now());
            skunk.setUpdatedOn(ZonedDateTime.now());
            skunk.setDeleted(Boolean.FALSE);
            postgresRepository.save(skunk);
        } else {
            Skunk skunk = optionalSkunk.get();

            skunk.setName(skunkDto.getName());
            skunk.setSize(skunkDto.getSize());
            skunk.setWeight(skunkDto.getWeight());
            skunk.setColor(skunkDto.getColor());
            skunk.setFamilyName(skunkDto.getFamilyName());
            skunk.setOmnivorous(skunkDto.getOmnivorous());

            skunk.setUpdatedOn(ZonedDateTime.now());
            postgresRepository.save(skunk);
        }
    }

    public void editAtMdb(SkunkDto skunkDto) {
        Optional<Skunk> optional = mariaDBRepository.findBySkunkIdAndIsDeletedFalse(skunkDto.getSkunkId());
        if(!optional.isPresent()) {

            Skunk skunk = skunkMapper.mapToEntity(skunkDto);
            skunk.setSkunkId(skunkDto.getSkunkId());
            skunk.setCreatedOn(ZonedDateTime.now());
            skunk.setUpdatedOn(ZonedDateTime.now());
            skunk.setDeleted(Boolean.FALSE);
            mariaDBRepository.save(skunk);
        } else {
            Skunk skunk = optional.get();

            skunk.setName(skunkDto.getName());
            skunk.setSize(skunkDto.getSize());
            skunk.setWeight(skunkDto.getWeight());
            skunk.setColor(skunkDto.getColor());
            skunk.setFamilyName(skunkDto.getFamilyName());
            skunk.setOmnivorous(skunkDto.getOmnivorous());

            skunk.setUpdatedOn(ZonedDateTime.now());

            mariaDBRepository.save(skunk);
        }
    }


}
