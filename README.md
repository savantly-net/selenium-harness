# selenium-harness
[![Build Status](https://travis-ci.org/savantly-net/selenium-harness.svg?branch=master)](https://travis-ci.org/savantly-net/selenium-harness)

![Selenium](./src/main/resources/public/modules/core/img/brand/big-logo.png?raw=true)  

[Demo Site](https://selenium-harness.herokuapp.com/)  

[Get the source!](https://github.com/savantly-net/selenium-harness)  

[Continuous Integration](https://travis-ci.org/savantly-net/selenium-harness) 

## Run
pre-requisites
* java
	
	$ java -jar selenium-harness.jar
	
You can place an `application.properties` file in the same directory as the jar to override default properties.
For example -

<pre> 
Directory of C:\temp\example 
   application.properties 
   selenium-harness.jar 
</pre> 

or you can create a properties file for different profiles by passing the command line parameter -

<pre> 
Directory of C:\temp\example 
   application.properties 
   application-prod.properties 
   selenium-harness.jar 
</pre> 

    $ java -jar selenium-harness.jar --spring.profiles.active=prod


## Build  
pre-requisites
* nodejs, bower, maven, java

    $ mvn package

## Create collections [scenes] of test cases [scenarios]  

Selenium-Harness provides a web interface to manage automated UI testing, leveraging Selenium + PhantomJS + GhostDriver  

Individual tests, or scenarios, can be executed or added to a scene for group execution.  

Currently the output is printed to the screen, but more API functions will be added soon, for integration with other systems, such as Jenkins for continous integration.  

With some modification, this may also prove useful for an automated monitoring platform with integration endpoints for systems like Bosun or Grafana  

### Scenarios
Scenarios can be either JavaScripts or the Selenium IDE [html] scripts.  
You can copy and paste the contents of your Selenium IDE tests by saving them as html files 
[Selenium IDE](http://www.seleniumhq.org/docs/02_selenium_ide.jsp)

![Scenarios](./screenshots/scenarioList.PNG?raw=true)  

Edit Scenario  

![Report Processors](./screenshots/editScenario.PNG?raw=true)  

### Report Processors
Report Processors are JavaScript function bodies that are executed in the current document context of the scenario, with the results of the scenerio passed as an argument to the processor.

![Report Processors](./screenshots/editReportProcessor.PNG?raw=true)  

### Scenes
With a *Scene* you can create a playlist of scenarios.

![Report Processors](./screenshots/editScene.PNG?raw=true)  

### ScenarioListener  
Java Beans can be added at run-time to do post processing by implementing [ScenarioListener](./src/main/java/net/savantly/selenium/harness/modules/scenario/ScenarioListener.java)  
An included Graphite processor emits Pass/Fail statistics for each test case thats run.  

![Grafana](./screenshots/GraphiteIntegration.PNG?raw=true)  

## Spring Boot  
Selenium-Harness is a SpringBoot application, so you can modify the application.properties file for most settings, including the preferred DB connection.



## Default Properties 

	### Servlet
	server.port=3031
	server.contextPath=/
	
	### for pretty printing of json when endpoints accessed over HTTP
	http.mappers.jsonPrettyPrint=true
	
	### Configuring info endpoint using maven properties
	info.app.name=@project.name@
	info.app.description=@project.description@
	info.app.version=@project.version@
	
	### Security
	security.user.password=password
	
	### Embedded Selenium Server 
	selenium.server.port=4444
	selenium.server.debug=true
	selenium.server.enabled=true
	
	### Have PhantomJS Use embedded Selenium Server by default
	phantomjs.selenium_hub_host=localhost
	phantomjs.selenium_hub_port=4444
	
	### Datasource for Test Case persistence
	spring.h2.console.enabled=true
	spring.datasource.username=sa
	spring.datasource.password=
	spring.datasource.driverClassName=org.h2.Driver
	spring.datasource.url=jdbc:h2:mem:selenium_harness
	spring.jpa.hibernate.ddl-auto=create-drop
	
	### Scenario Listeners
	savantly.listener.packages=net.savantly.selenium.harness.listeners.graphite
	
	### Graphite Configuration
	graphite.host=127.0.0.1
	graphite.metric.prefix=sharness
	graphite.metric.match[0]=[^a-zA-Z\\d\\s\\.:]
	graphite.metric.replace[0]=
	
	### Fixtures
	savantly.fixtures=true
	savantly.fixtures.reportProcessor=true
	savantly.fixtures.scenario=true
	
	### Logging 
	logging.level.root=INFO
	logging.level.org.apache.http.wire=INFO
	logging.level.net.savantly=DEBUG
	spring.output.ansi.enabled=ALWAYS
		