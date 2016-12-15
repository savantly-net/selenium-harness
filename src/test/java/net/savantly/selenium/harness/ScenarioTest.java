package net.savantly.selenium.harness;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import net.savantly.selenium.harness.modules.scenario.ScenarioExecutor;
import net.savantly.selenium.harness.modules.scenario.ScenarioItem;

@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ScenarioTest {
	@Autowired
	ScenarioExecutor executor;

	@Test
	public void test() throws IOException {
		ScenarioItem testCase = new ScenarioItem();
		testCase.setName("test1");
		testCase.setUrl("https://www.google.com");
		testCase.setScript("return document.documentElement.innerText;");
		Object result = executor.execute(testCase);
		System.out.println(result);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testSelenese() throws IOException {
		
		String selenese = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\"> <head profile=\"http://selenium-ide.openqa.org/profiles/test-case\"> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /> <link rel=\"selenium.base\" href=\"https://www.google.com/\" /> <title>GoogleSearch</title> </head> <body> <table cellpadding=\"1\" cellspacing=\"1\" border=\"1\"> <thead> <tr><td rowspan=\"1\" colspan=\"3\">GoogleSearch</td></tr> </thead><tbody> <tr> 	<td>open</td> 	<td>http://google.com</td> 	<td></td> </tr> <tr> 	<td>selectWindow</td> 	<td>null</td> 	<td></td> </tr> <tr> 	<td>type</td> 	<td>id=lst-ib</td> 	<td>selenium tests</td> </tr>  </tbody></table> </body> </html> ";
		
		ScenarioItem testCase = new ScenarioItem();
		testCase.setName("test2. with other characters in name > ??\\");
		testCase.setUrl("https://www.google.com");
		testCase.setScript(selenese);
		Object result = executor.execute(testCase);
		System.out.println(result);
		Assert.assertNotNull(result);
	}


}
