package net.savantly.selenium.harness.modules.scenario;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import net.savantly.selenium.harness.modules.reportProcessor.ReportProcessor;

@Entity
public class ScenarioItem {

	@Id
	@GeneratedValue
	private UUID id;
	private String url;
	@Lob
	private String script;
	private String name;
	private String description;
	@ManyToOne
	private ReportProcessor reportProcessor;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ReportProcessor getReportProcessor() {
		return reportProcessor;
	}

	public void setReportProcessor(ReportProcessor reportProcessor) {
		this.reportProcessor = reportProcessor;
	}

}
