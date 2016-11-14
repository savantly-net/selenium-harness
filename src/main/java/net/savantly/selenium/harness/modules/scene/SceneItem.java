package net.savantly.selenium.harness.modules.scene;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import net.savantly.selenium.harness.modules.scenario.ScenarioItem;

@Entity
public class SceneItem {
	
	@Id
	@GeneratedValue
	private UUID id;
	private String name;
	private String description;
	@ManyToMany
	private List<ScenarioItem> scenarios;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ScenarioItem> getScenarios() {
		return scenarios;
	}
	public void setScenarios(List<ScenarioItem> scenarios) {
		this.scenarios = scenarios;
	}

}
