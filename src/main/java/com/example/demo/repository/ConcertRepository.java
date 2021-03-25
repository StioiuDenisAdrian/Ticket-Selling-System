package com.example.demo.repository;

import com.example.demo.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Integer> {
}
