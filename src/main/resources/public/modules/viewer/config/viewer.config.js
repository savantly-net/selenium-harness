// Configuring the module
angular.module('viewer').run(['Menus',
	function(Menus) {
		// Set top bar menu items
		Menus.addMenuItem('topbar', 'Views', '/viewers', 'dropdown', '/viewers', true);
		Menus.addSubMenuItem('topbar', '/viewers', 'List', 'viewers', 'viewers', true);
		Menus.addSubMenuItem('topbar', '/viewers', 'Create', 'viewers/create', 'createviewer', true);
	}
]);