package com.millysapp.repository.mariadb;

import com.millysapp.model.Skunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SkunkMariaDBRepository extends JpaRepository<Skunk, UUID> {

    List<Skunk> findAllByIsDeletedFalse();
    Optional<Skunk> findBySkunkIdAndIsDeletedFalse(UUID id);

}
