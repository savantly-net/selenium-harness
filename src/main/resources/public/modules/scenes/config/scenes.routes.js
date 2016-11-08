'use strict';

// Setting up route
angular.module('scenes').config(['$stateProvider', '$urlRouterProvider',
	function($stateProvider, $urlRouterProvider) {
		$stateProvider.
		state('scenes', {
			url: '/scenes',
			templateUrl: 'modules/scenes/views/list.view.html'
		}).
		state('createScene', {
			url:'/scenes/create',
			templateUrl: 'modules/scenes/views/edit.view.html'
		}).
		state('editScene', {
			url:'/scenes/:id/edit',
			templateUrl: 'modules/scenes/views/edit.view.html'
		});
	}
]);