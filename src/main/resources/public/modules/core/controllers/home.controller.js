'use strict';


angular.module('core').controller('HomeController', ['$scope', '$rootScope', 'Authentication',
	function($scope, $rootScope, Authentication) {
		// This provides Authentication context.
		$rootScope.title='Selenium Harness';
		$scope.authentication = Authentication;
	}
]);