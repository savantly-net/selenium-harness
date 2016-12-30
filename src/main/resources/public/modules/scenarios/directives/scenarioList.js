angular.module('scenarios')
.directive('scenarioList', ['$filter', function($filter) {
	return {
		restrict: 'E',
		scope: {
			scenarios: '=',
			search: '=',
			executeScenario: '&',
	    },
	    link: function(scope, elements, attrs){
	    	var scenarioExecutor = scope.executeScenario();
	    	
	    	scope.selectAll = function(value){
				var filteredScenarios = $filter('filter')(scope.scenarios, scope.search);
				for(var i=0; i<filteredScenarios.length; i++){
					filteredScenarios[i].checked = value;
				}
			};
			scope.executeSelectedScenarios = function(){
				for (var scenarioIndex = 0; scenarioIndex < scope.scenarios.length; scenarioIndex++) {
					var scenario = scope.scenarios[scenarioIndex];
					if(scenario.checked){
						scenarioExecutor(scenario);
					}
				}
			};
	    },
	    templateUrl: 'modules/scenarios/views/templates/scenario.list.html'
	};
}]);	