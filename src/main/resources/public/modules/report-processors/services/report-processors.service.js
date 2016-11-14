'use strict';

//service used to communicate with REST endpoints
angular.module('reportProcessors').factory('ReportProcessors', ['$resource', '$http', 
	function($resource, $http) {
		return $resource('/reportProcessors/:id', { id: '@id' }, {
			query: {
				isArray: false,
			}
		});
	}
]);