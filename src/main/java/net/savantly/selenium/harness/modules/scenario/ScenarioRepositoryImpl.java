package net.savantly.selenium.harness.modules.scenario;

import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;

import net.savantly.selenium.harness.modules.tag.Tag;

public class ScenarioRepositoryImpl implements ScenarioRepositoryCustom{

	@Autowired
	EntityManager em;
	
	@Modifying
	public <S extends ScenarioItem> S save(S scenario) {
		Set<Tag> tags = scenario.getTags();
		for (Tag tag : tags) {
			em.merge(tag);
		}
		S savedItem = em.merge(scenario);
		em.flush();
		return savedItem;
	}
}
