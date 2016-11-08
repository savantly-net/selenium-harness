'use strict';


angular.module('scenes').controller('ScenesController', ['$scope', '$rootScope', '$stateParams', '$location', 'Authentication', 'Scenes', 'Scenarios', 
	function($scope, $rootScope, $stateParams, $location, Authentication, Scenes, Scenarios) {
		// This provides Authentication context.
		$rootScope.title='Scenes';
		$scope.authentication = Authentication;
		$scope.loading = [];
		$scope.console = "";
		$scope.item = {scenarios:[]};
		
		Scenes.query().$promise.then(function(response){
			$scope.scenes = response.content;
		});
		
		Scenarios.query().$promise.then(function(response){
			$scope.availableScenarios = response.content;
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
			var scene = new Scenes ($scope.item);

			// Redirect after save
			scene.$save(function(response) {
				$location.path('scenes/' + response.id + '/edit');
				$scope.removeLoader('save');
			}, function(errorResponse) {
				$scope.error = errorResponse.data.message;
				$scope.removeLoader('save');
			});
		};
		
		$scope.addScenario = function(scenario){
			$scope.item.scenarios.push(scenario);
		}
		
		$scope.removeScenario = function(scenario){
			var scenarioIndex = $scope.item.scenarios.indexOf(scenario);
			if(scenarioIndex>-1){
				$scope.item.scenarios.splice(scenarioIndex, 1);
			}
		}
		
		$scope.cancel = function(){
			$location.path('scenes/');
		};
		
		$scope.executeSelectedScenes = function(){
			for (var itemIndex = 0; itemIndex < $scope.scenes.length; itemIndex++) {
				var scene = $scope.scenes[itemIndex];
				if(scene.checked){
					$scope.executeScene(scene);
				}
			}
		}
		
		// Find existing item
		$scope.findOne = function() {
			Scenes.get({
				id: $stateParams.id
			}).$promise.then(function(item){
				$scope.item = item;
			});
		};
		
		$scope.log = function(msg){
			$scope.console = $scope.console + '\n' + msg;
		}
		
		$scope.executeScene = function(scene){
			scene.loading = true;
			var loaderId = 'execute-' + scene.name;
			$scope.addLoader(loaderId);
			Scenes.execute(scene).$promise.then(function(response){
				scene.loading = false;
				$scope.removeLoader(loaderId);
				var msg = 'scene: ' + scene.name + ' status code:' + response.httpStatusCode + ' Result: ' + response.scenarioResults;
				$scope.log(msg);
			});
		};
	}
]);