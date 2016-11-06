package net.savantly.selenium.harness.config;

import java.io.File;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import net.anthavio.phanbedder.Phanbedder;

@Configuration
public class PhantomJsConfiguration {
	private static File phantomjs = Phanbedder.unpack();
	public static final String PHANTOM_EXEC_PATH = phantomjs.getAbsolutePath();
	

	private Capabilities capability;
	
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public WebDriver getWebDriver(){
		DesiredCapabilities desiredCaps = new DesiredCapabilities();
		desiredCaps.merge(capability);
		desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PhantomJsConfiguration.PHANTOM_EXEC_PATH);

		desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX, "page.settings");
		desiredCaps.setCapability("page.settings.XSSAuditingEnabled", true);
		desiredCaps.setCapability("page.settings.javascriptCanCloseWindows", true);
		desiredCaps.setCapability("page.settings.javascriptCanOpenWindows", true);
		desiredCaps.setCapability("page.settings.javascriptEnabled", true);
		desiredCaps.setCapability("page.settings.loadImages", true);
		desiredCaps.setCapability("page.settings.localToRemoteUrlAccessEnabled", true);
		
		desiredCaps.setCapability("takesScreenshot", false);
		desiredCaps.setCapability("handlesAlerts", false);
		desiredCaps.setCapability("javascriptEnabled", true);
		desiredCaps.setCapability("databaseEnabled", true);
		desiredCaps.setCapability("locationContextEnabled", true);
		desiredCaps.setCapability("applicationCacheEnabled", true);
		desiredCaps.setCapability("browserConnectionEnabled", true);
		desiredCaps.setCapability("cssSelectorsEnabled", true);
		desiredCaps.setCapability("webStorageEnabled", true);
		desiredCaps.setCapability("rotatable", true);
		desiredCaps.setCapability("acceptSslCerts", true);
		desiredCaps.setCapability("nativeEvents", true);
		
		
		// Control LogLevel for GhostDriver, via CLI arguments
		desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
				new String[] { "--logLevel=DEBUG" });
		
        WebDriver driver = new PhantomJSDriver(desiredCaps);
		return driver;
	}

}
