package net.savantly.selenium.harness;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.SeleniumServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import net.savantly.selenium.harness.listeners.AppListener;

@SpringBootApplication
public class Application {
	
    @SuppressWarnings("unused")
	private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Application.class, args);
    }
    
    @Bean
    ApplicationListener getAppListener(WebDriver webdriver, SeleniumServer seleniumServer){
    	return new AppListener(webdriver, seleniumServer);
    }

}
