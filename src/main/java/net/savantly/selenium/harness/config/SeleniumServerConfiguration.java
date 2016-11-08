package net.savantly.selenium.harness.config;

import javax.annotation.PostConstruct;

import org.openqa.grid.internal.utils.configuration.StandaloneConfiguration;
import org.openqa.selenium.remote.server.SeleniumServer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="selenium.server")
public class SeleniumServerConfiguration {

	private StandaloneConfiguration serverConfig = new StandaloneConfiguration();
	private Integer browserTimeout;
	private boolean debug;
	private boolean help;
	private Integer jettyMaxThreads;
	private String log;
	private Integer port;
	private String role;
	private Integer timeout;
	private boolean enabled;
	private SeleniumServer seleniumServer;
	
	@PostConstruct
	private SeleniumServer startSeleniumServer() {
		serverConfig.browserTimeout = browserTimeout;
		serverConfig.debug = debug;
		serverConfig.help = help;
		serverConfig.jettyMaxThreads = jettyMaxThreads;
		serverConfig.log = log;
		serverConfig.port = port;
		serverConfig.role = role;
		serverConfig.timeout = timeout;
		
	    try {
	    	seleniumServer = new SeleniumServer(serverConfig);
	    	seleniumServer.boot();
	    } catch (Exception e) {
	        throw new IllegalArgumentException("Failed to start embedded Selenium Server",e);
	    }
	    return seleniumServer;
	}
	
	@Bean
	public SeleniumServer seleniumServerBean(){
		return seleniumServer;
	}

	public Integer getBrowserTimeout() {
		return browserTimeout;
	}

	public void setBrowserTimeout(Integer browserTimeout) {
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

	public Integer getJettyMaxThreads() {
		return jettyMaxThreads;
	}

	public void setJettyMaxThreads(Integer jettyMaxThreads) {
		this.jettyMaxThreads = jettyMaxThreads;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
