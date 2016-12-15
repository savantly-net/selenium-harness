package net.savantly.selenium.harness.modules.scenario;

public interface ScenarioRepositoryCustom {
	
	<S extends ScenarioItem> S save(S scenario);

}
