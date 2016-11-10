'use strict';


angular.module('scenarios').controller('ScenariosController', ['$scope', '$rootScope', '$log', '$stateParams', '$location', 'Authentication', 'Scenarios', 
	function($scope, $rootScope, $log, $stateParams, $location, Authentication, Scenarios) {
		// This provides Authentication context.
		$rootScope.title='Scenarios';
		$scope.authentication = Authentication;
		$scope.$location = $location;
		$scope.loading = [];
		$scope.console = '';
		$scope.logs = [];
		
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
		
		$scope.duplicate = function(scenario){
			$scope.item = scenario;
			$scope.item.id = null;
			$scope.item.name = $scope.item.name + ' COPY';
			$scope.save();
		}
		
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
			$scope.logs.push(msg);
			$scope.console = angular.toJson($scope.logs, true);
		}
		$scope.clearLogs = function(){
			$scope.logs = [];
			$scope.console = angular.toJson($scope.logs, true);
		}
		
		$scope.executeScenario = function(scenario){
			scenario.loading = true;
			var loaderId = 'execute-' + scenario.name;
			$scope.addLoader(loaderId);
			Scenarios.execute(scenario).$promise.then(function(response){
				scenario.loading = false;
				$scope.removeLoader(loaderId);
				scenario.error = false;
				var msg = {scenario: scenario.name, response:  response};
				scenario.response = angular.toJson(msg, true);
				$scope.log(msg);
			}).catch(function(errorResponse){
				scenario.loading = false;
				$scope.removeLoader(loaderId);
				scenario.error = errorResponse.data.message;
				scenario.response = false;
			})
		};
		
		$scope.deleteScenario = function(scenario){
			var _scenario = new Scenarios(scenario);
			_scenario.$delete().then(function(response){
				var scenarioIndex = $scope.scenarios.indexOf(scenario);
				if (scenarioIndex > -1){
					$scope.scenarios.splice(scenarioIndex, 1);
				}
			});
		};
		
		$scope.selectAll = function(value){
			for(var i=0; i<$scope.scenarios.length; i++){
				$scope.scenarios[i].checked = value;
			}
		};
	}
]);