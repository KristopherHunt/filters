package com.ekhunt.backend.repository;

import com.ekhunt.backend.model.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
}
