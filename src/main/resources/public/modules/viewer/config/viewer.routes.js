'use strict';

// Setting up route
angular.module('viewer').config(['$stateProvider', '$urlRouterProvider',
	function($stateProvider, $urlRouterProvider) {
		$stateProvider.
		state('viewers', {
			url: '/viewers',
			templateUrl: 'modules/viewer/views/list.view.html'
		}).
		state('createViewer', {
			url:'/viewers/create',
			templateUrl: 'modules/viewer/views/edit.view.html'
		}).
		state('createViewerWithRedirect', {
			url: '/viewers/create/:redirectState?',
			templateUrl: 'modules/viewer/views/edit.view.html'
		}).
		state('editViewer', {
			url:'/viewers/:id/edit',
			templateUrl: 'modules/viewer/views/edit.view.html'
		}).
		state('editViewerWithRedirect', {
			url:'/viewers/:id/edit/:redirectState?',
			templateUrl: 'modules/viewer/views/edit.view.html'
		});
	}
]);