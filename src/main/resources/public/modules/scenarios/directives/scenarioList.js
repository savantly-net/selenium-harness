angular.module('scenarios')
.directive('scenarioList', function() {
	return {
		restrict: 'E',
		scope: {
			scenarios: '=',
			search: '='
	    },
	    templateUrl: 'modules/scenarios/views/templates/scenario.list.html'
	};
});	