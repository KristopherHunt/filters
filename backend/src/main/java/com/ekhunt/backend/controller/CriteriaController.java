package com.ekhunt.backend.controller;

import com.ekhunt.backend.model.Criteria;
import com.ekhunt.backend.service.CriteriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/criteria")
public class CriteriaController {

    private final CriteriaService criteriaService;

    public CriteriaController(CriteriaService criteriaService){
        this.criteriaService = criteriaService;
    }

    @GetMapping
    public ResponseEntity<List<Criteria>> getAllCriteria() {
        List<Criteria> criteria = criteriaService.findAllCriteria();
        return new ResponseEntity<>(criteria, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<List<Criteria>> addCriteria(@RequestBody List<Criteria> criteria) {
        List<Criteria> newCriteria = criteriaService.addCriteria(criteria);
        return new ResponseEntity<>(newCriteria, HttpStatus.OK);
    }
}
