package net.savantly.selenium.harness.modules.scene;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.savantly.selenium.harness.rest.BaseController;

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
