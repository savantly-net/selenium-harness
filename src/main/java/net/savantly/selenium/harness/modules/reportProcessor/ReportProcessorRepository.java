package net.savantly.selenium.harness.modules.reportProcessor;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReportProcessorRepository extends PagingAndSortingRepository<ReportProcessor, UUID>{

	List<ReportProcessor> findByName(String name);
}
