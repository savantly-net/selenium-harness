package net.savantly.selenium.harness.config;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

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
	
	@Bean(name="exampleSelenese")
	public String sHarness() throws IOException{
		ClassPathResource resource = new ClassPathResource("/examples/selenese.html");
		byte[] contents = FileCopyUtils.copyToByteArray(resource.getInputStream());
		return new String(contents);
	};

}
