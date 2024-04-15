package com.ekhunt.backend.model;
import jakarta.persistence.*;

@Entity
@Table(name = "filter")
public class Filter {
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

    public Filter(){
    }

    public Filter(String type, String comparison, String value) {
        this.type = type;
        this.comparison = comparison;
        this.value = value;
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
}

