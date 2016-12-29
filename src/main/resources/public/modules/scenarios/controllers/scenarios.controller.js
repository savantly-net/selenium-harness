'use strict';


angular.module('scenarios').controller('ScenariosController', 
		['$scope', '$rootScope', '$filter', '$log', '$stateParams', '$location', '$mdDialog', '$mdToast', 'Authentication', 'Scenarios', 'ReportProcessors', 'TestAggregator', 'Viewers',    
	function($scope, $rootScope, $filter, $log, $stateParams, $location, $mdDialog, $mdToast, Authentication, Scenarios, ReportProcessors, TestAggregator, Viewers) {
		// This provides Authentication context.
		$rootScope.title='Scenarios';
		$scope.authentication = Authentication;
		$scope.$location = $location;
		$scope.loading = [];
		$scope.console = '';
		$scope.logs = [];
		$scope.reportProcessors = [];
		$scope.showLog = false;
		$scope.tabs = [];
		
		var executionSuccessHandler = function(response){
			this.loading = false;
			$scope.removeLoader(this.loaderId);
			this.error = false;
			var msg = {scenario: this.name, response: response};
			this.response = angular.toJson(msg, true);
			this.passed = TestAggregator.didPass(response);
			$scope.log(msg);
		};
		var executionFailedHandler = function(errorResponse){
			this.scenario.loading = false;
			$scope.removeLoader(this.loaderId);
			this.scenario.error = errorResponse.data.message;
			this.scenario.response = false;
		};
		
		Scenarios.query().$promise.then(function(response){
			$scope.scenarios = response.content;
		});
		ReportProcessors.query().$promise.then(function(response){
			$scope.reportProcessors = $scope.reportProcessors.concat(response.content);
		});
		Viewers.query().$promise.then(function(response){
			response.content.map(function(viewer){
				var tab = {title: viewer.name};
				tab.search = {};
				viewer.matchers.map(function(matcher){
					tab.search = matcher.matchText;
				});
				$scope.tabs.push(tab);
			});
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
				$mdToast.show($mdToast.simple().textContent('Saved'));
			}, function(errorResponse) {
				if(errorResponse.data && errorResponse.data.message){
					$scope.error = errorResponse.data.message;
				} else {
					$scope.error = 'Request Failed';	
				}
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
			$location.path('/scenarios');
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
			scenario.loaderId = 'execute-' + scenario.name;
			$scope.addLoader(scenario.loaderId);
			Scenarios.execute(scenario).$promise
				.then(executionSuccessHandler.bind(scenario)).catch(executionFailedHandler.bind(scenario));
		};
		
		$scope.testScenario = function(scenario){
			scenario.loading = true;
			scenario.loaderId = 'execute-' + scenario.name;
			$scope.addLoader(scenario.loaderId);
			Scenarios.test(scenario).$promise
				.then(executionSuccessHandler.bind(scenario)).catch(executionFailedHandler.bind(scenario));
		};
		
		$scope.deleteScenario = function(scenario){
			var dialog = $mdDialog.confirm().title('Delete Scenario')
				.textContent('Are you sure you want to delete "'+ scenario.name +'"?')
				.ok('Delete')
				.cancel('Cancel');
			$mdDialog.show(dialog).then(function(){
				var _scenario = new Scenarios(scenario);
				_scenario.$delete().then(function(response){
					var scenarioIndex = $scope.scenarios.indexOf(scenario);
					if (scenarioIndex > -1){
						$scope.scenarios.splice(scenarioIndex, 1);
					}
				});
			});
		};
		
		$scope.selectAll = function(value){
			var filteredScenarios = $filter('filter')($scope.scenarios, $scope.searchText);
			for(var i=0; i<filteredScenarios.length; i++){
				filteredScenarios[i].checked = value;
			}
		};
		
		$scope.changeReportProcessor = function(selectedReportProcessor){
			console.log(selectedReportProcessor);
		};
	}
]);