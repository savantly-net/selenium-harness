angular.module('scenarios')
.controller('scenarioListController', ['$scope', function($scope) {
}])
.directive('scenarioList', function() {
	return {
		restrict: 'E',
		scope: {
			scenarios: '=scenarios'
	    },
	    templateUrl: 'modules/scenarios/views/templates/scenario.list.html'
	};
});	