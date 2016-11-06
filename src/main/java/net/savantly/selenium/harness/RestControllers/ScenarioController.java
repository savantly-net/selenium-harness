package net.savantly.selenium.harness.RestControllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class ScenarioController {
	
	@Autowired
	ScenarioRepository repository;
	@Autowired
	ScenarioExecutor executor;

	@RequestMapping({"/", ""})
	public Page<ScenarioItem> getAll(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	@RequestMapping(value={"/", "", "/{id}"}, method=RequestMethod.POST)
	public ScenarioItem save(@RequestBody ScenarioItem sTest){
		return repository.save(sTest);
	}
	
	@RequestMapping("/{id}")
	public ScenarioItem getOne(@PathVariable UUID id){
		return repository.findOne(id);
	}
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public Object test(@RequestBody ScenarioItem scenarioItem){
		return executor.execute(scenarioItem);
	}
	
	@RequestMapping(value="/{id}/execute", method=RequestMethod.GET)
	public ScenarioResult execute(@PathVariable("id") ScenarioItem scenarioItem){
		return executor.execute(scenarioItem);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable UUID id){
		repository.delete(id);;
	}
}
