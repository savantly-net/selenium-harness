package net.savantly.selenium.harness.modules.scenario;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ScenarioRepository extends PagingAndSortingRepository<ScenarioItem, UUID>{

	List<ScenarioItem> findByName(String name);

}
