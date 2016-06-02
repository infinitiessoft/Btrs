angular.module('hello', [ 'ngRoute' ]).config(function($routeProvider, $httpProvider) {

	$routeProvider
	.when('/login', {
		templateUrl : 'login.html',
		controller : 'navigation',
		controllerAs: 'controller'
	}).when('/',{
                templateUrl : 'pages/homescreen.html',
                controller  : 'mainController'
	}) .when('/about', {
        templateUrl : 'about.html',
        controller  : 'aboutController'
    }).when('/create', {
        templateUrl : 'createReport/createReport.html',
        controller  : 'createController'
    })
    .when('/report', {
        templateUrl : 'report.html',
        controller  : 'reportController'
    })
    .when('/expense', {
        templateUrl : 'expense/expense.html',
        controller  : 'expenseController'
    })
    .when('/photo', {
        templateUrl : 'expense/photo.html',
        controller  : 'photoController'
    });

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).controller('navigation',
		  function($rootScope, $scope, $http, $location) {
	  var authenticate = function(callback) {
	    $http.get('rest/userShared').success(function(data) {
	      if (data.username) {
	        $rootScope.authenticated = true;
	      } else {
	        $rootScope.authenticated = false;
	      }
	      callback && callback();
	    }).error(function() {
	      $rootScope.authenticated = false;
	      callback && callback();
	    });
	  }
	  authenticate();
	  $scope.credentials = {};
	  $scope.login = function() {
	    $http.post('login', $.param($scope.credentials), {
	 
	      headers : {
	        "content-type" : "application/x-www-form-urlencoded"
	      }
	    }).success(function(data) {
	      authenticate(function() {
	        if ($rootScope.authenticated) {
	          $location.path("/");
	          $scope.error = false;
	        } else {
	          $location.path("/");
	          $scope.error = true;
	        }
	      });
	    }).error(function(data) {
	      $location.path("/login");
	      $scope.error = true;
	      $rootScope.authenticated = false;
	    })
	  };
	

			self.logout = function() {
				$http.post('logout', {}).finally(function() {
					$rootScope.authenticated = false;
					$location.path("/");
				});
			}

		}).controller('hello', function($scope) {
//	var self = this;
//	$http.get('/Btrs/home.html').then(function(response) {
//		self.greeting = response.data;
			 $scope.message = 'Look! I am an about page.';
	});
	 
