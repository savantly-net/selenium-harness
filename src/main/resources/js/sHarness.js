// Add sHarness to global scope
sHarness = {}; 
sHarness.httpStatusCode = arguments[0];
sHarness.headers = {};

var headerKeys = arguments[1];
var headerValues = arguments[2];

for (var headerKeyIndex = 0; headerKeyIndex < headerKeys.length; headerKeyIndex++){
	var key = headerKeys[headerKeyIndex];
	if (key != null){
		var value;
		if(headerValues[headerKeyIndex].length == 1){
			value = headerValues[headerKeyIndex][0];
		} else {
			value = headerValues[headerKeyIndex];
		}
		sHarness.headers[key] = value;
	}
};
