package net.savantly.selenium.harness.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.savantly.selenium.harness.domain.scene.SceneItem;
import net.savantly.selenium.harness.domain.scene.SceneResult;
import net.savantly.selenium.harness.repository.SceneRepository;
import net.savantly.selenium.harness.service.SceneExecutor;

@RestController
@RequestMapping("/scenes")
public class SceneController extends BaseController<SceneItem, SceneRepository>{
	
	@Autowired
	SceneRepository repository;
	@Autowired
	SceneExecutor executor;
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public SceneResult test(@RequestBody SceneItem sceneItem){
		return executor.execute(sceneItem);
	}
	
	@RequestMapping(value="/{id}/execute", method=RequestMethod.GET)
	public SceneResult execute(@PathVariable("id") SceneItem sceneItem){
		return executor.execute(sceneItem);
	}

}
