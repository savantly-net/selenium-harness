package net.savantly.selenium.harness.modules.scenario;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import net.savantly.selenium.harness.modules.PersistedModule;
import net.savantly.selenium.harness.modules.reportProcessor.ReportProcessor;
import net.savantly.selenium.harness.modules.tag.Tag;

@Entity
public class ScenarioItem extends PersistedModule {

	private static final long serialVersionUID = 2140552210097962111L;
	private String url;
	@Lob
	private String script;
	private String name;
	private String description;
	@ManyToOne
	private ReportProcessor reportProcessor;
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Tag> tags = new HashSet<>();

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
	
	public void addTag(String... tag){
		for (String tag2 : tag) {
			addTag(tag2);
		}
	}
	
	public void addTag(String tag){
		this.tags.add(new Tag(tag));
	}
	
	public Set<Tag> getTags(){
		return this.tags;
	}

}
