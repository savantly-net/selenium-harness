package net.savantly.selenium.harness.RestControllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import net.savantly.selenium.harness.domain.scenario.ScenarioItem;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ScenarioControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@WithMockUser(username = "user", password = "password")
	public void testGetAll() {
		ResponseEntity<ScenarioItem> response = this.restTemplate.getForEntity("/scenarios/", ScenarioItem.class);
	}

	@Test
	@WithMockUser(username = "user", password = "password")
	public void testSave() {
		ScenarioItem stest = new ScenarioItem();
		stest.setUrl("https://google.com");
		stest.setScript("return document.title");
		stest.setName("Test1");
		ResponseEntity<ScenarioItem> response = this.restTemplate.postForEntity("/scenarios/", stest, ScenarioItem.class);
		
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertEquals(stest.getName(), response.getBody().getName());
		Assert.assertEquals(stest.getUrl(), response.getBody().getUrl());
		Assert.assertEquals(stest.getScript(), response.getBody().getScript());
		
		// Update Name and id
		stest.setName("NewTestName");
		stest.setId(response.getBody().getId());
		
		ResponseEntity<ScenarioItem> newResponse = this.restTemplate.postForEntity("/scenarios/", stest, ScenarioItem.class);		
		Assert.assertEquals("NewTestName", newResponse.getBody().getName());
		
	}

}
