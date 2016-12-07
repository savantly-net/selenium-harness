package net.savantly.selenium.harness.config;

import javax.annotation.PostConstruct;

//import org.openqa.grid.internal.utils.configuration.StandaloneConfiguration;
import org.openqa.selenium.remote.server.SeleniumServer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ConfigurationProperties(prefix="selenium.server")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SeleniumServerConfiguration {

	//private StandaloneConfiguration serverConfig = new StandaloneConfiguration();
	private int browserTimeout;
	private boolean debug;
	private boolean help;
	private int jettyMaxThreads;
	private String log;
	private int port;
	private String role;
	private int timeout;
	private boolean enabled;
	private SeleniumServer seleniumServer;
	private boolean started = false;
	
	@PostConstruct
	private void startSeleniumServer() {
		if(started){
			throw new RuntimeException("Embedded Selenium Server is already started.");
		}
/*		serverConfig.browserTimeout = browserTimeout;
		serverConfig.debug = debug;
		serverConfig.help = help;
		serverConfig.jettyMaxThreads = jettyMaxThreads;
		serverConfig.log = log;
		serverConfig.port = port;
		serverConfig.role = role;
		serverConfig.timeout = timeout;*/
		
	    try {
	    	seleniumServer = new SeleniumServer(port);
	    	seleniumServer.setBrowserTimeout(browserTimeout);
	    	seleniumServer.setSessionTimeout(timeout);
	    	seleniumServer.setThreadCount(jettyMaxThreads);
	    	seleniumServer.boot();
	    	started = true;
	    } catch (Exception e) {
	        throw new IllegalArgumentException("Failed to start embedded Selenium Server", e);
	    }
	}
	
	@Bean
	public SeleniumServer seleniumServerBean(){
		return seleniumServer;
	}

	public int getBrowserTimeout() {
		return browserTimeout;
	}

	public void setBrowserTimeout(int browserTimeout) {
		this.browserTimeout = browserTimeout;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isHelp() {
		return help;
	}

	public void setHelp(boolean help) {
		this.help = help;
	}

	public int getJettyMaxThreads() {
		return jettyMaxThreads;
	}

	public void setJettyMaxThreads(int jettyMaxThreads) {
		this.jettyMaxThreads = jettyMaxThreads;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
