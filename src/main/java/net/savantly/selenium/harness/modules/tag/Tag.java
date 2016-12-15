package net.savantly.selenium.harness.modules.tag;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag {

	@Id
	private String text;
	
	public Tag(){}

	public Tag(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
