'use strict';


angular.module('scenarios').controller('ScenariosController', ['$scope', '$rootScope', '$stateParams', '$location', 'Authentication', 'Scenarios', 
	function($scope, $rootScope, $stateParams, $location, Authentication, Scenarios) {
		// This provides Authentication context.
		$rootScope.title='Scenarios';
		$scope.authentication = Authentication;
		$scope.loading = [];
		$scope.console = "";
		
		Scenarios.query().$promise.then(function(response){
			$scope.scenarios = response.content;
		});
		
		$scope.addLoader = function(id){
			$scope.loading.push(id);
		};
		
		$scope.removeLoader = function(id){
			var loaderIndex = $scope.loading.indexOf(id);
			if(loaderIndex > -1){
				$scope.loading.splice(loaderIndex, 1);
			}
		};
		
		$scope.save = function() {
			$scope.addLoader('save');
			// Create new object
			var scenario = new Scenarios ($scope.item);

			// Redirect after save
			scenario.$save(function(response) {
				$location.path('scenarios/' + response.id + '/edit');
				$scope.removeLoader('save');
			}, function(errorResponse) {
				$scope.error = errorResponse.data.message;
				$scope.removeLoader('save');
			});
		};
		
		$scope.cancel = function(){
			$location.path('scenarios/');
		};
		
		$scope.executeSelectedScenarios = function(){
			for (var scenarioIndex = 0; scenarioIndex < $scope.scenarios.length; scenarioIndex++) {
				var scenario = $scope.scenarios[scenarioIndex];
				if(scenario.checked){
					$scope.executeScenario(scenario);
				}
			}
		}
		
		// Find existing item
		$scope.findOne = function() {
			Scenarios.get({
				id: $stateParams.id
			}).$promise.then(function(item){
				$scope.item = item;
			});
		};
		
		$scope.log = function(msg){
			$scope.console = $scope.console + '\n' + msg;
		}
		
		$scope.executeScenario = function(scenario){
			scenario.loading = true;
			var loaderId = 'execute-' + scenario.name;
			$scope.addLoader(loaderId);
			Scenarios.execute(scenario).$promise.then(function(response){
				scenario.loading = false;
				$scope.removeLoader(loaderId);
				var msg = 'scenario: ' + scenario.name + ' status code:' + response.httpStatusCode + ' Result: ' + response.scriptResult;
				$scope.log(msg);
			});
		};
	}
]);