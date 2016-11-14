package net.savantly.selenium.harness.modules.scene;

import java.util.ArrayList;
import java.util.List;

import net.savantly.selenium.harness.modules.scenario.ScenarioResult;

public class SceneResult {
	
	private List<ScenarioResult> scenarioResults = new ArrayList<ScenarioResult>();

	public List<ScenarioResult> getScenarioResults() {
		return scenarioResults;
	}
	
	public void addScenarioResult(ScenarioResult... result){
		for (ScenarioResult scenarioResult : result) {
			this.scenarioResults.add(scenarioResult);
		}
	}
	
	public void clearScenarioResults(){
		this.scenarioResults.clear();
	}

}
