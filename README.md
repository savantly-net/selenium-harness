# selenium-harness

![Selenium](https://github.com/savantly-net/selenium-harness/blob/master/src/main/resources/public/modules/core/img/brand/big-logo.png?raw=true)  

## Create collections [scenes] of test cases [scenarios]  

Selenium-Harness provides a web interface to manage automated UI testing, leveraging Selenium + PhantomJS + GhostDriver  

Individual tests, or scenarios, can be executed or added to a scene for group execution.  

Currently the output is printed to the screen, but more API functions will be added soon, for integration with other systems, such as Jenkins for continous integration.  

With some modification, this may also prove useful for an automated monitoring platform with integration endpoints for systems like Bosun or Grafana  

### Scenarios
Scenarios can be either JavaScripts or the Selenium IDE [html] scripts.  
You can copy and paste the contents of your Selenium IDE tests by saving them as html files 
[Selenium IDE](http://www.seleniumhq.org/docs/02_selenium_ide.jsp)

![Scenarios](https://github.com/savantly-net/selenium-harness/blob/master/screenshots/scenarioList.PNG?raw=true)  

## Spring Boot  
Selenium-Harness is a SpringBoot application, so you can modify the application.properties file for most settings, including the preferred DB connection.



## Properties 

```
##### Servlet
server.port=3030
server.contextPath=/

##### for pretty printing of json when endpoints accessed over HTTP
http.mappers.jsonPrettyPrint=true

##### Configuring info endpoint using maven properties
info.app.name=@project.name@
info.app.description=@project.description@
info.app.version=@project.version@

##### Security
security.user.password=password

##### Embedded Selenium Server 
selenium.server.port=4444
selenium.server.debug=true
selenium.server.enabled=true

##### Have PhantomJS Use embedded Selenium Server by default
phantomjs.selenium-hub=127.0.0.1
phantomjs.selenium-hub-port=4444

##### Datasource for Test Case persistence
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:~/db/selenium-harness;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
```