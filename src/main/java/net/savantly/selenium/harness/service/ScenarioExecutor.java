package net.savantly.selenium.harness.service;

import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import net.savantly.selenium.harness.domain.scenario.ScenarioItem;
import net.savantly.selenium.harness.domain.scenario.ScenarioResult;

@Service
public class ScenarioExecutor {

	private static final Logger log = LoggerFactory.getLogger(ScenarioExecutor.class);

	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	WebDriver driver;

	public ScenarioResult execute(ScenarioItem testCase) {
		//WebDriver driver = applicationContext.getBean(WebDriver.class);
		ScenarioResult result = new ScenarioResult();
		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection con = (HttpURLConnection) new URL(testCase.getUrl()).openConnection();
			con.setRequestMethod("HEAD");
			result.setHttpStatusCode(con.getResponseCode());
			driver.get(testCase.getUrl());
			result.setScriptResult(((JavascriptExecutor) driver).executeScript(testCase.getScript()));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			//driver.quit();
		}
		return result;
	}

}
