package com.example.repo;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Summary;

@Repository
public interface SummaryRepository extends CrudRepository<Summary, Serializable> {

}
