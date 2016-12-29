package net.savantly.selenium.harness.modules.view;

import javax.persistence.Entity;

import net.savantly.selenium.harness.modules.PersistedModule;

@Entity
public class ViewMatcher extends PersistedModule{
	
	private MatchType matchType;
	private String matchText;
	
	public MatchType getMatchType() {
		return matchType;
	}
	public void setViewType(MatchType matchType) {
		this.matchType = matchType;
	}
	public String getMatchText() {
		return matchText;
	}
	public void setMatchText(String matchText) {
		this.matchText = matchText;
	}
}
