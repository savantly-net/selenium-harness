package net.savantly.selenium.harness.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import net.savantly.spring.fixture.Fixture;

@Configuration
@ConditionalOnProperty(prefix="savantly", name={"fixtures"})
public class FixtureConfiguration {
	
	@Autowired
	List<Fixture<?>> fixtures;
	
	@PostConstruct
	public void installFixtures(){
		for (Fixture<?> fixture : fixtures) {
			fixture.install();
		}
	}
}
