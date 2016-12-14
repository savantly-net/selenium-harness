'use strict';

//Configuring the module
angular.module('material').config(['$mdThemingProvider', 
    function($mdThemingProvider) {
		$mdThemingProvider.alwaysWatchTheme(true);
	    $mdThemingProvider.theme('default')
	    .primaryPalette('blue')
	    .accentPalette('green');
	}
]);