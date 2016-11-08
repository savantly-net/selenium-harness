package net.savantly.selenium.harness.listeners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.SeleniumServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class AppListener implements ApplicationListener<ContextStoppedEvent> {
	
	@Autowired
	WebDriver webdriver;
	@Autowired
	SeleniumServer seleniumServer;

	@Override
	public void onApplicationEvent(ContextStoppedEvent event) {
		webdriver.quit();
		seleniumServer.stop();
	}

}
