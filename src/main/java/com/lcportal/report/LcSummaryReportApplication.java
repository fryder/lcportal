package com.lcportal.report;

import com.lcportal.report.entity.Summary;
import com.lcportal.report.repository.SummaryRepository;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class LcSummaryReportApplication {

	@Autowired
	SummaryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(LcSummaryReportApplication.class, args);
	}

//	@Bean
//	@Scope("singleton")
//	public DataProvider vaadinGridDataProvider() {
//		return    DataProvider.fromCallbacks((CallbackDataProvider.FetchCallback<Summary, Void>) query -> repository.findAll().stream(),
//				(CallbackDataProvider.CountCallback<Summary, Void>) query -> Integer.valueOf(String.valueOf(repository.count())));
//	}

}
