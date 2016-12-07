package net.savantly.selenium.harness.modules.scenario;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import net.savantly.spring.fixture.AbstractBaseFixture;
import net.savantly.spring.fixture.Fixture;

@Service
@ConditionalOnProperty(prefix="savantly.fixtures", name={"scenario"})
public class ScenarioItemFixture extends AbstractBaseFixture<ScenarioItem, ScenarioRepository> {

	@Autowired
	@Qualifier("exampleSelenese")
	private String selenese;
	private ScenarioRepository repository;
	private ScenarioItem seleneseScenario;
	private ScenarioItem jsScenario;
	
	@PostConstruct
	public void post(){
		seleneseScenario = new ScenarioItem();
		seleneseScenario.setName("Selense Example");
		seleneseScenario.setDescription("An example selense test");
		seleneseScenario.setUrl("http://google.com");
		seleneseScenario.setScript(selenese);
    	
		jsScenario = new ScenarioItem();
		jsScenario.setName("JS Example");
		jsScenario.setDescription("An example JS test");
		jsScenario.setUrl("http://google.com");
		jsScenario.setScript("return document.title;");
	}
	
    @Autowired
    public ScenarioItemFixture(ScenarioRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void addEntities(List<ScenarioItem> entityList) {
        log.info("Adding Entities to Fixture");
        List<ScenarioItem> seleneseSearch = this.repository.findByName(seleneseScenario.getName());
        if(seleneseSearch.size() == 0){
        	entityList.add(seleneseScenario);
        }
        List<ScenarioItem> jsSearch = this.repository.findByName(jsScenario.getName());
        if(jsSearch.size() == 0){
        	entityList.add(jsScenario);
        }
    }

    @Override
    public void addDependencies(List<Fixture<?>> dependencies) {
        // There are no fixture dependencies
    }
}
