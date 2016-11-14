package net.savantly.selenium.harness.modules.scenario;

public class ScenarioResult {
	
	private int httpStatusCode;
	private Object scriptResult;
	
	public int getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	public Object getScriptResult() {
		return scriptResult;
	}
	public void setScriptResult(Object scriptResult) {
		this.scriptResult = scriptResult;
	}

}
