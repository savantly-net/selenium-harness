package net.savantly.selenium.harness.modules.scene;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.savantly.selenium.harness.modules.scenario.ScenarioExecutor;
import net.savantly.selenium.harness.modules.scenario.ScenarioItem;

@Service
public class SceneExecutor {

	@Autowired
	ScenarioExecutor scenarioExecutor;
	
	public SceneResult execute(SceneItem scene){
		SceneResult sceneResult = new SceneResult();
		for (ScenarioItem scenarioItem : scene.getScenarios()) {
			sceneResult.addScenarioResult(scenarioExecutor.execute(scenarioItem));
		}
		 return sceneResult;
	}
}
