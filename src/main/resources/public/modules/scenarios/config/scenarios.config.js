// Configuring the module
angular.module('scenarios').run(['Menus',
	function(Menus) {
		// Set top bar menu items
		Menus.addMenuItem('topbar', 'Scenarios', '/scenarios', 'dropdown', '/scenarios', true);
		Menus.addSubMenuItem('topbar', '/scenarios', 'List', 'scenarios', 'scenarios', true);
		Menus.addSubMenuItem('topbar', '/scenarios', 'Create', 'scenarios/create', 'createScenario', true);
	}
]);