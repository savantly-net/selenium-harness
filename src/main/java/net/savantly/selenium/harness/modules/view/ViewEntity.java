package net.savantly.selenium.harness.modules.view;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import net.savantly.selenium.harness.modules.PersistedModule;

@Entity
public class ViewEntity extends PersistedModule{
	
	private String name;
	private String description;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<ViewMatcher> matchers = new HashSet<>();;

	public Set<ViewMatcher> getMatchers() {
		return matchers;
	}

	public void setMatchers(Set<ViewMatcher> matchers) {
		this.matchers = matchers;
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
}
