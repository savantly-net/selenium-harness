// Configuring the module
angular.module('scenes').run(['Menus',
	function(Menus) {
		// Set top bar menu items
		Menus.addMenuItem('topbar', 'Scenes', '/scenes', 'dropdown', '/scenes', true);
		Menus.addSubMenuItem('topbar', '/scenes', 'List', 'scenes', 'scenes', true);
		Menus.addSubMenuItem('topbar', '/scenes', 'Create', 'scenes/create', 'createScene', true);
	}
]);