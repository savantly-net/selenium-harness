'use strict';

//service used to communicate with REST endpoints
angular.module('scenes').factory('Scenes', ['$resource', '$http', 
	function($resource, $http) {
		return $resource('/scenes/:id', { id: '@id' }, {
			query: {
				isArray: false,
			},
			execute: {
				isArray: false,
				url: '/scenes/:id/execute'
			}
		});
	}
]);