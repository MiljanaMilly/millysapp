package com.millysapp.repository.mariadb;

import com.millysapp.model.Skunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkunkMariaDBRepository extends JpaRepository<Skunk, Long> {

    List<Skunk> findAll();
}
