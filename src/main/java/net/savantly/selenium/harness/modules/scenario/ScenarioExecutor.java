package net.savantly.selenium.harness.modules.scenario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import jp.vmi.selenium.selenese.Runner;
import jp.vmi.selenium.selenese.TestCase;
import jp.vmi.selenium.selenese.result.Result;
import net.savantly.selenium.harness.config.ListenerConfiguration;
import net.savantly.selenium.harness.config.PhantomJsConfiguration;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ScenarioExecutor {

	private static final Logger log = LoggerFactory.getLogger(ScenarioExecutor.class);

	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	PhantomJsConfiguration phantomJsConfiguration;
	@Autowired
	ListenerConfiguration listenerConfiguration;
	@Autowired 
	@Qualifier("sHarness")
	String sHarness;

	public ScenarioResult execute(ScenarioItem testCase) {
		Map<String, ScenarioListener> scenarioListeners = applicationContext.getBeansOfType(ScenarioListener.class);
		WebDriver driver = null;
		ScenarioResult result = new ScenarioResult();
		result.setName(testCase.getName());
		try {
			driver = phantomJsConfiguration.getWebDriver();
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
				// Add sHarness object to global scope for tests.
				// Header keys and values are made available in the sHarness object for the tests and report processors
				jsExecutor.executeScript(sHarness, con.getResponseCode(), con.getHeaderFields().keySet(), con.getHeaderFields().values());
				Object scriptResult = jsExecutor.executeScript(testCase.getScript());
				result.setScriptResult(scriptResult);
			}
			
			if(testCase.getReportProcessor() != null){
				Object finalResult = jsExecutor.executeScript(testCase.getReportProcessor().getScript(), result.getScriptResult().toString());
				result.setScriptResult(finalResult);
			}
			result.setFailed(!didTheTestPass(result.getScriptResult()));
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			result.setScriptResult(e.getMessage());
			result.setFailed(true);
		} finally {
			if(driver != null){
				driver.quit();
			}
		}
		if(scenarioListeners != null && !(scenarioListeners.isEmpty())){
			for (String key : scenarioListeners.keySet()){
				scenarioListeners.get(key).onComplete(result);
			}
		}
		return result;
	}

	private boolean didTheTestPass(Object scriptResult) {
		String className = scriptResult.getClass().getSimpleName().toUpperCase();
		try{
			switch (className){
				case "BOOLEAN":
					return (boolean) scriptResult;
				case "SUCCESS": return true;
				case "TRANSFORMEDENTRIESMAP":
					Map<String, Object> map = (Map<String, Object>) scriptResult;
					Set<String> keys = map.keySet();
					log.info("Script Result Keys: {}", keys.toString());
					if(map.get("success") == Boolean.valueOf(true)) return true;
					if(map.get("passed") == Boolean.valueOf(true)) return true;
					if(map.get("failed") == Boolean.valueOf(false)) return true;
				case "STRING":
					return true;
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return false;
	}

}
