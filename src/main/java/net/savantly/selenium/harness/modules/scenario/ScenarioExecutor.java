package net.savantly.selenium.harness.modules.scenario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import jp.vmi.selenium.selenese.Runner;
import jp.vmi.selenium.selenese.TestCase;
import jp.vmi.selenium.selenese.result.Result;

@Service
public class ScenarioExecutor {

	private static final Logger log = LoggerFactory.getLogger(ScenarioExecutor.class);

	@Autowired
	ApplicationContext applicationContext;

	public ScenarioResult execute(ScenarioItem testCase) {
		
		WebDriver driver = applicationContext.getBean(WebDriver.class);
		ScenarioResult result = new ScenarioResult();
		try {
			JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
			if(testCase.getScript().startsWith("<?xml")){
				Runner seleneseRunner = new Runner();
				seleneseRunner.setDriver(driver);
				TestCase seTest = new TestCase();
				File tempFile = File.createTempFile("sel", ".tmp");
				FileWriter fileWriter = new FileWriter(tempFile, true);
			    System.out.println(tempFile.getAbsolutePath());
			    BufferedWriter bw = new BufferedWriter(fileWriter);
			    bw.write(testCase.getScript());
			    bw.close();
				
				seTest.initialize(tempFile.getAbsolutePath(), testCase.getName(), testCase.getUrl());
				Result seleneseResult = seleneseRunner.execute(seTest);
				result.setScriptResult(seleneseResult);
				tempFile.delete();
			} else {
				HttpURLConnection.setFollowRedirects(false);
				HttpURLConnection con = (HttpURLConnection) new URL(testCase.getUrl()).openConnection();
				con.setRequestMethod("HEAD");
				result.setHttpStatusCode(con.getResponseCode());
				driver.get(testCase.getUrl());
				Object scriptResult = jsExecutor.executeScript(testCase.getScript());
				result.setScriptResult(scriptResult);
			}
			
			if(testCase.getReportProcessor() != null){
				Object finalResult = jsExecutor.executeScript(testCase.getReportProcessor().getScript(), result.getScriptResult().toString());
				result.setScriptResult(finalResult);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			driver.quit();
		}
		return result;
	}

}
