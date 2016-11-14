package net.savantly.selenium.harness.modules.scenario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.savantly.selenium.harness.rest.BaseController;

@RestController
@RequestMapping("/scenarios")
public class ScenarioController extends BaseController<ScenarioItem, ScenarioRepository>{
	
	@Autowired
	ScenarioExecutor executor;
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public ScenarioResult test(@RequestBody ScenarioItem scenarioItem){
		return this.executor.execute(scenarioItem);
	}
	
	@RequestMapping(value="/{id}/execute", method={RequestMethod.GET, RequestMethod.POST})
	public ScenarioResult execute(@PathVariable("id") ScenarioItem scenarioItem){
		return this.executor.execute(scenarioItem);
	}
}
