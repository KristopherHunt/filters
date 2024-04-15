package com.ekhunt.backend.controller;

import com.ekhunt.backend.model.Filter;
import com.ekhunt.backend.service.FilterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/filters")
public class FilterController {

    private final FilterService filterService;

    public FilterController(FilterService filterservice){
        this.filterService = filterservice;
    }

    @GetMapping
    public ResponseEntity<List<Filter>> getAllFilters() {
        List<Filter> filters = filterService.findAllFilters();
        return new ResponseEntity<>(filters, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Filter> addFilter(@RequestBody Filter filter) {
        Filter newFilter = filterService.addFilter(filter);
        return new ResponseEntity<>(newFilter, HttpStatus.OK);
    }
}
