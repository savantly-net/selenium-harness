'use strict';

// Setting up route
angular.module('reportProcessors').config(['$stateProvider', '$urlRouterProvider',
	function($stateProvider, $urlRouterProvider) {
		$stateProvider.
		state('reportProcessors', {
			url: '/reportProcessors',
			templateUrl: 'modules/report-processors/views/list.view.html'
		}).
		state('createReportProcessor', {
			url:'/reportProcessors/create',
			templateUrl: 'modules/report-processors/views/edit.view.html'
		}).
		state('editReportProcessor', {
			url:'/reportProcessors/:id/edit',
			templateUrl: 'modules/report-processors/views/edit.view.html'
		});
	}
]);