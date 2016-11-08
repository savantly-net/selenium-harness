package net.savantly.selenium.harness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.savantly.selenium.harness.domain.scenario.ScenarioItem;
import net.savantly.selenium.harness.domain.scene.SceneItem;
import net.savantly.selenium.harness.domain.scene.SceneResult;

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
