package com.ekhunt.backend.repository;

import com.ekhunt.backend.model.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterRepository extends JpaRepository<Filter, Long> {
}
