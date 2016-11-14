package net.savantly.selenium.harness.modules.scenario;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ScenarioRepository extends PagingAndSortingRepository<ScenarioItem, UUID>{

}
