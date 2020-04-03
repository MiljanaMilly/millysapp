package com.millysapp.repository.postgres;

import com.millysapp.model.Skunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SkunkPostgresRepository extends JpaRepository<Skunk, UUID> {

    List<Skunk> findAllByIsDeletedFalse();
    Optional<Skunk> findBySkunkIdAndIsDeletedFalse(UUID id);
}
