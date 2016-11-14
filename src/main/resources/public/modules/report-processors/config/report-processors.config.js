// Configuring the module
angular.module('reportProcessors').run(['Menus',
	function(Menus) {
		// Set top bar menu items
		Menus.addMenuItem('topbar', 'Reports', '/reportProcessors', 'dropdown', '/reportProcessors', true);
		Menus.addSubMenuItem('topbar', '/reportProcessors', 'List Report Processors', 'reportProcessors', 'reportProcessors', true);
		Menus.addSubMenuItem('topbar', '/reportProcessors', 'Create Report Processor', 'reportProcessors/create', 'createReportProcessor', true);
	}
]);