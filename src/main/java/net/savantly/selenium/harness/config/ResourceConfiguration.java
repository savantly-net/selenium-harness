package net.savantly.selenium.harness.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

@Configuration
public class ResourceConfiguration {
	
	ClassLoader cl = ResourceConfiguration.class.getClassLoader();
	
	private String getResourceContents(String string) throws IOException {
		String url = new ClassPathResource(string).getPath();
		InputStream resource = cl.getResourceAsStream(url);
		byte[] contents = FileCopyUtils.copyToByteArray(resource);
		return new String(contents);
	}
	
	@Bean(name="sHarness")
	public String sHarness() throws IOException{
		return getResourceContents("/js/sHarness.js");
	};
	
	@Bean(name="sHarnessAPI")
	public String sHarnessAPI() throws IOException{
		return getResourceContents("/public/modules/global/sHarnessAPI.js");
	};
	
	@Bean(name="exampleSelenese")
	public String exampleSelenese() throws IOException{
		return getResourceContents("/examples/selenese.html");
	};

}
