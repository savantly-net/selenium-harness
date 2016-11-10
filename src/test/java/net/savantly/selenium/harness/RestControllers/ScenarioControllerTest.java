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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import net.savantly.selenium.harness.domain.scenario.ScenarioItem;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
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
	public void testSaveJavaScript() {
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
	
	@Test
	@WithMockUser(username = "user", password = "password")
	public void testSaveSelenese() {
		String selenese = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\"> <head profile=\"http://selenium-ide.openqa.org/profiles/test-case\"> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /> <link rel=\"selenium.base\" href=\"https://www.google.com/\" /> <title>GoogleSearch</title> </head> <body> <table cellpadding=\"1\" cellspacing=\"1\" border=\"1\"> <thead> <tr><td rowspan=\"1\" colspan=\"3\">GoogleSearch</td></tr> </thead><tbody> <tr> 	<td>open</td> 	<td>http://google.com</td> 	<td></td> </tr> <tr> 	<td>selectWindow</td> 	<td>null</td> 	<td></td> </tr> <tr> 	<td>type</td> 	<td>id=lst-ib</td> 	<td>selenium tests</td> </tr>  </tbody></table> </body> </html> ";
		
		ScenarioItem stest = new ScenarioItem();
		stest.setUrl("https://google.com");
		stest.setScript(selenese);
		stest.setName("Selenese Test");
		ResponseEntity<ScenarioItem> response = this.restTemplate.postForEntity("/scenarios/", stest, ScenarioItem.class);
		
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertEquals(stest.getName(), response.getBody().getName());
		Assert.assertEquals(stest.getUrl(), response.getBody().getUrl());
		Assert.assertEquals(stest.getScript(), response.getBody().getScript());
		
		// Update Name and id
		stest.setName("Updated Selenese Test");
		stest.setId(response.getBody().getId());
		
		ResponseEntity<ScenarioItem> newResponse = this.restTemplate.postForEntity("/scenarios/", stest, ScenarioItem.class);		
		Assert.assertEquals("Updated Selenese Test", newResponse.getBody().getName());
		
	}

}
