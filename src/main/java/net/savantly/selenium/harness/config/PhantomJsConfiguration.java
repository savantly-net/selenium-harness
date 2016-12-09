package net.savantly.selenium.harness.config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import net.anthavio.phanbedder.Phanbedder;

@Configuration
@ConfigurationProperties(prefix="phantomjs")
public class PhantomJsConfiguration {
	private static File phantomjs = Phanbedder.unpack();
	public static final String PHANTOM_EXEC_PATH = phantomjs.getAbsolutePath();
	
	@Autowired
	SeleniumServerConfiguration seleniumServerConfig;
	private String seleniumHubHost;
	private String seleniumHubPort;
	

	private Capabilities capability;
	
	public WebDriver getWebDriver() throws MalformedURLException{
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
		
		desiredCaps.setBrowserName("phantomjs");
		
		String seleniumHub = "http://" + seleniumHubHost + ":" + seleniumHubPort;
		
		// Control LogLevel for GhostDriver, via CLI arguments
		desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
				new String[] { "--logLevel=DEBUG",  "--webdriver-selenium-grid-hub=" + seleniumHub});
		
        //WebDriver driver = new PhantomJSDriver(desiredCaps);
		WebDriver driver = new RemoteWebDriver(new URL(seleniumHub + "/wd/hub"), desiredCaps);
		return driver;
	}

	public String getSeleniumHubHost() {
		return seleniumHubHost;
	}

	public void setSeleniumHubHost(String seleniumHubHost) {
		this.seleniumHubHost = seleniumHubHost;
	}

	public String getSeleniumHubPort() {
		return seleniumHubPort;
	}

	public void setSeleniumHubPort(String seleniumHubPort) {
		this.seleniumHubPort = seleniumHubPort;
	}

}
