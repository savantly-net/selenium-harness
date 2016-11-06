'use strict';

// Setting up route
angular.module('scenarios').config(['$stateProvider', '$urlRouterProvider',
	function($stateProvider, $urlRouterProvider) {
		$stateProvider.
		state('scenarios', {
			url: '/scenarios',
			templateUrl: 'modules/scenarios/views/list.view.html'
		}).
		state('createScenario', {
			url:'/scenarios/create',
			templateUrl: 'modules/scenarios/views/edit.view.html'
		}).
		state('editScenario', {
			url:'/scenarios/:id/edit',
			templateUrl: 'modules/scenarios/views/edit.view.html'
		});
	}
]);