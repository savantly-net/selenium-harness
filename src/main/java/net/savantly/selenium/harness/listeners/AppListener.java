package net.savantly.selenium.harness.listeners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.SeleniumServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class AppListener implements ApplicationListener<ContextClosedEvent> {
	
	WebDriver webdriver;
	SeleniumServer seleniumServer;
	
	public AppListener(WebDriver webdriver, SeleniumServer seleniumServer){
		this.webdriver = webdriver;
		this.seleniumServer = seleniumServer;
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		webdriver.quit();
		seleniumServer.stop();
	}

}
