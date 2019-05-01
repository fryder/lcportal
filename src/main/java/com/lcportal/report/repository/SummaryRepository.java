package com.lcportal.report.repository;

import com.lcportal.report.entity.Summary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SummaryRepository extends MongoRepository<Summary,String> {


    public List<Summary> findByLastName(String lastName);

    public Summary findByLastNameStartsWithIgnoreCase(String filterText);
}
