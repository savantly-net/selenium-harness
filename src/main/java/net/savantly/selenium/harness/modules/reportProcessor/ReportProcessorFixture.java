package net.savantly.selenium.harness.modules.reportProcessor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import net.savantly.spring.fixture.AbstractBaseFixture;
import net.savantly.spring.fixture.Fixture;

@Service
@ConditionalOnProperty(prefix="savantly.fixtures", name={"reportProcessor"})
public class ReportProcessorFixture extends AbstractBaseFixture<ReportProcessor, ReportProcessorRepository> {

	private ReportProcessorRepository repository;
	private ReportProcessor defaultStatusProcessor;
	private ReportProcessor debugProcessor;
	
	{
		defaultStatusProcessor = new ReportProcessor();
    	defaultStatusProcessor.setName("Default Status Processor");
    	defaultStatusProcessor.setDescription("passes if the status is less than 400");
    	defaultStatusProcessor.setScript("return {passed: sHarness.httpStatusCode < 400};");
    	
    	debugProcessor = new ReportProcessor();
    	debugProcessor.setName("Debug Processor");
    	debugProcessor.setDescription("Debugs the test case results");
    	debugProcessor.setScript("return {arguments: arguments, sHarness: sHarness};");
    }
	
    @Autowired
    public ReportProcessorFixture(ReportProcessorRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void addEntities(List<ReportProcessor> entityList) {
        log.info("Adding Entities to Fixture");
        List<ReportProcessor> defaultSearch = this.repository.findByName(defaultStatusProcessor.getName());
        if(defaultSearch.size() == 0){
        	entityList.add(defaultStatusProcessor);
        }
        List<ReportProcessor> debugSearch = this.repository.findByName(debugProcessor.getName());
        if(debugSearch.size() == 0){
        	entityList.add(debugProcessor);
        }
    }

    @Override
    public void addDependencies(List<Fixture<?>> dependencies) {
        // There are no fixture dependencies
    }
}
