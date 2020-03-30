package com.millysapp.repository.postgres;

import com.millysapp.model.Skunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkunkPostgresRepository extends JpaRepository<Skunk, Long> {

    List<Skunk> findAll();
}
