package com.ekhunt.backend.service;

import com.ekhunt.backend.model.Criteria;
import com.ekhunt.backend.repository.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriteriaService {

    private final CriteriaRepository criteriaRepository;

    @Autowired
    public CriteriaService(CriteriaRepository criteriaRepository) {
        this.criteriaRepository = criteriaRepository;
    }

    public List<Criteria> addCriteria(List<Criteria> criteria) {
        return criteriaRepository.saveAll(criteria);
    }

    public List<Criteria> findAllCriteria() {
        return criteriaRepository.findAll();
    }
}
