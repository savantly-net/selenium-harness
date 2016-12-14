package net.savantly.selenium.harness.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import net.savantly.selenium.harness.modules.scenario.ScenarioListener;

@Configuration
@ConfigurationProperties("savantly.listener")
public class ListenerConfiguration {
	private static final Logger log = LoggerFactory.getLogger(ListenerConfiguration.class);
	
	@Autowired
	ApplicationContext applicationContext;
	String[] packages;
	
	ClassPathBeanDefinitionScanner scanner;
	BeanDefinitionRegistry beanRegistry;

	@PostConstruct
	public void post(){
        beanRegistry = (BeanDefinitionRegistry) this.applicationContext.getAutowireCapableBeanFactory();
		scanner = new ClassPathBeanDefinitionScanner(beanRegistry);
		getScenarioListeners();
	}
	
	public void getScenarioListeners() throws BeansException {
		TypeFilter tf = new AssignableTypeFilter(ScenarioListener.class);
		scanner.addIncludeFilter(tf);
		int beansFound = scanner.scan(packages); 
		log.info("Scenario Listeners found: {}", beansFound);
	}

	public void setPackages(String[] packages) {
		this.packages = packages;
	}

}
