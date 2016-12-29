package net.savantly.selenium.harness.modules.view;

import javax.persistence.Entity;

import net.savantly.selenium.harness.modules.PersistedModule;

@Entity
public class ViewMatcher extends PersistedModule{
	
	private MatchType matchType;
	private String matchText;
	
	public MatchType getViewType() {
		return matchType;
	}
	public void setViewType(MatchType viewType) {
		this.matchType = viewType;
	}
	public String getMatchText() {
		return matchText;
	}
	public void setMatchText(String matchText) {
		this.matchText = matchText;
	}
}
