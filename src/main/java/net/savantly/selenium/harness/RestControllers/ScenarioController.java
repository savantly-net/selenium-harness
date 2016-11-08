package net.savantly.selenium.harness.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.savantly.selenium.harness.domain.scenario.ScenarioItem;
import net.savantly.selenium.harness.domain.scenario.ScenarioResult;
import net.savantly.selenium.harness.repository.ScenarioRepository;
import net.savantly.selenium.harness.service.ScenarioExecutor;

@RestController
@RequestMapping("/scenarios")
public class ScenarioController extends BaseController<ScenarioItem, ScenarioRepository>{
	
	@Autowired
	ScenarioRepository repository;
	@Autowired
	ScenarioExecutor executor;
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public ScenarioResult test(@RequestBody ScenarioItem scenarioItem){
		return executor.execute(scenarioItem);
	}
	
	@RequestMapping(value="/{id}/execute", method=RequestMethod.GET)
	public ScenarioResult execute(@PathVariable("id") ScenarioItem scenarioItem){
		return executor.execute(scenarioItem);
	}
}
