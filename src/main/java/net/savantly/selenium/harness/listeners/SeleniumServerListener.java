package net.savantly.selenium.harness.listeners;

import org.openqa.selenium.remote.server.SeleniumServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class SeleniumServerListener implements ApplicationListener<ContextClosedEvent> {
	
	SeleniumServer seleniumServer;
	
	public SeleniumServerListener(SeleniumServer seleniumServer){
		this.seleniumServer = seleniumServer;
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		seleniumServer.stop();
	}

}
