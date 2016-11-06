package net.savantly.selenium.harness;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import net.savantly.selenium.harness.domain.scenario.ScenarioItem;
import net.savantly.selenium.harness.service.ScenarioExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ScenarioTest {
	@Autowired
	ScenarioExecutor executor;

	@Test
	public void test() throws IOException {
		ScenarioItem testCase = new ScenarioItem();
		testCase.setUrl("https://www.google.com");
		testCase.setScript("return document.documentElement.innerText;");
		Object result = executor.execute(testCase);
		System.out.println(result);
	}

}
