package com.ekhunt.backend.controller;

import com.ekhunt.backend.model.Filter;
import com.ekhunt.backend.service.FilterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/filter")
public class FilterController {

    private final FilterService filterService;

    public FilterController(FilterService filterService){
        this.filterService = filterService;
    }

    @GetMapping
    public ResponseEntity<List<Filter>> getAllFilter() {
        List<Filter> filter = filterService.findAllFilters();
        return new ResponseEntity<>(filter, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Filter> addFilter(@RequestBody Filter filter) {
        Filter newFilter = filterService.addFilter(filter);
        return new ResponseEntity<>(newFilter, HttpStatus.OK);
    }
}
