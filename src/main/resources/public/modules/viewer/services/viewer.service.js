'use strict';

//service used to communicate with REST endpoints
angular.module('viewer').factory('Viewers', ['$resource', '$http', 
	function($resource, $http) {
		return $resource('/viewers/:id', { id: '@id' }, {
			query: {
				isArray: false,
			},
			getMatchTypes: {
				isArray: true,
				method: 'GET',
				url: '/viewers/matchTypes'
			}
		});
	}
]);