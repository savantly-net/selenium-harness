package net.savantly.selenium.harness.listeners.graphite;

import java.net.SocketException;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

import net.savantly.graphite.CarbonMetric;
import net.savantly.graphite.GraphiteClient;
import net.savantly.graphite.GraphiteClientFactory;
import net.savantly.graphite.impl.SimpleCarbonMetric;
import net.savantly.selenium.harness.modules.scenario.ScenarioListener;
import net.savantly.selenium.harness.modules.scenario.ScenarioResult;

public class GraphiteScenarioListener implements ScenarioListener{
	
	@Value("${graphite.host}")
	private String graphiteHost;
	@Value("${graphite.metric.prefix}")
	private String metricPrefix;
	GraphiteClient graphiteClient;
	
	@PostConstruct
	public void post() throws UnknownHostException, SocketException{
		graphiteClient = GraphiteClientFactory.defaultGraphiteClient(graphiteHost);
	}

	@Override
	public void onComplete(ScenarioResult result) {
		if(result.getName() == null) return;
		
		long epoch = System.currentTimeMillis()/1000;
		String metricName = metricPrefix + "." + result.getName().toLowerCase().replaceAll("[^a-zA-Z\\d\\s\\.:]", "").replace(' ', '_');
		metricName = ensureNoDotsOnEnds(metricName);
		String value = result.isFailed() ? "0":"1";
        CarbonMetric carbonMetric = new SimpleCarbonMetric(metricName, value, epoch);
		graphiteClient.saveCarbonMetrics(carbonMetric);
	}

	private String ensureNoDotsOnEnds(String metricName) {
		while(metricName.startsWith(".")){
			metricName = metricName.substring(1);
		}
		while(metricName.endsWith(".")){
			metricName = metricName.substring(0, metricName.length()-1);
		}
		return metricName;
	}

	public void setGraphiteHost(String graphiteHost) {
		this.graphiteHost = graphiteHost;
	}

}
