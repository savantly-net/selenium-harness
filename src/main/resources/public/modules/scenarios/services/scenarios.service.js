'use strict';

//service used to communicate with REST endpoints
angular.module('scenarios').factory('Scenarios', ['$resource', '$http', 
	function($resource, $http) {
		return $resource('/scenarios/:id', { id: '@id' }, {
			query: {
				isArray: false,
			},
			execute: {
				isArray: false,
				url: '/scenarios/:id/execute'
			}
		});
	}
]);