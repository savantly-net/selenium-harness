/**
 * http://usejsdoc.org/
 */

var sHarnessAPI = function(){
	
	this.didPass = function(testResponse) {
		if(testResponse == true) return true;
		if(testResponse.failed) return false;
		if(testResponse && testResponse.scriptResult == true) return true;
		if(testResponse && testResponse.scriptResult && 
				(testResponse.scriptResult.passed || testResponse.scriptResult.success )) return true;
		if(testResponse && testResponse.scriptResult && 
				(testResponse.scriptResult.passed == undefined || testResponse.scriptResult.success == undefined )) return true;
		return false;
	};
	
	return this;
}();