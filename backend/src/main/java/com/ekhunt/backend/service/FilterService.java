package com.ekhunt.backend.service;

import com.ekhunt.backend.model.Filter;
import com.ekhunt.backend.repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterService {

    private final FilterRepository filterRepository;

    @Autowired
    public FilterService(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

    public Filter addFilter(Filter filter) {
        return filterRepository.save(filter);
    }

    public List<Filter> findAllFilters() {
        return filterRepository.findAll();
    }
}
