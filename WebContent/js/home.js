angular.module('home', []).controller('home', function($scope, $http) {

	$http
	.get('rest/v1.0/auth')
	.success(
			function(data) {
//				console.debug(JSON.stringify(data));
			}).error(function() {
//				console.debug('error');
		
	});
});