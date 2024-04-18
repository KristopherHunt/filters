package com.ekhunt.backend.model;
import jakarta.persistence.*;

@Entity
@Table(name = "criteria")
public class Criteria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false, updatable = false)
    private String type;
    @Column(nullable = false, updatable = false)
    private String comparison;
    @Column(nullable = false, updatable = false)
    private String value;
    @ManyToOne
    @JoinColumn(name = "under_filter")
    private Filter filter;

    public Criteria(){
    }

    public Criteria(String type, String comparison, String value, Filter filter) {
        this.type = type;
        this.comparison = comparison;
        this.value = value;
        this.filter = filter;
    }

    public String getType(){
        return type;
    }

    public String getComparison() {
        return comparison;
    }

    public String getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}

