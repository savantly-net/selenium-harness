'use strict';

angular.module('core').factory('TestAggregator', [
	function() {

		// Public API
		this.didPass = sHarnessAPI.didPass;

		return this;
	}
]);