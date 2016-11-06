package net.savantly.selenium.harness.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import net.savantly.selenium.harness.domain.scenario.ScenarioItem;

public interface ScenarioRepository extends PagingAndSortingRepository<ScenarioItem, UUID>{

}
