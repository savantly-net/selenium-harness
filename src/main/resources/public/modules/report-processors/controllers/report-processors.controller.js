'use strict';


angular.module('reportProcessors').controller('ReportProcessorsController', ['$scope', '$rootScope', '$stateParams', '$location', 'Authentication', 'ReportProcessors',  
	function($scope, $rootScope, $stateParams, $location, Authentication, ReportProcessors) {
		// This provides Authentication context.
		$rootScope.title='Report Processors';
		$scope.authentication = Authentication;
		$scope.loading = [];
		$scope.$location = $location;
		$scope.item = {};		
		$scope.console = '';
		$scope.logs = [];
		$scope.fromJson = angular.fromJson;
		$scope.toJson = angular.toJson;
		
		ReportProcessors.query().$promise.then(function(response){
			$scope.items = response.content;
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
			var item = new ReportProcessors ($scope.item);

			// Redirect after save
			item.$save(function(response) {
				$location.path('reportProcessors/' + response.id + '/edit');
				$scope.removeLoader('save');
			}, function(errorResponse) {
				$scope.error = errorResponse.data.message;
				$scope.removeLoader('save');
			});
		};
		
		$scope.duplicate = function(item){
			$scope.item = item;
			$scope.item.id = null;
			$scope.item.name = $scope.item.name + ' COPY';
			$scope.save();
		}
		
		$scope.cancel = function(){
			$location.path('/reportProcessors');
		};
		
		
		// Find existing item
		$scope.findOne = function() {
			ReportProcessors.get({
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
		
	}
]);