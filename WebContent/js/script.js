// create the module and name it scotchApp
    var scotchApp = angular.module('scotchApp', ['ngRoute']);

    // configure our routes
    scotchApp.config(function($routeProvider) {
        $routeProvider

            // route for the home page
            .when('/', {
                templateUrl : 'pages/homescreen.html',
                controller  : 'mainController'
            })

            // route for the about page
            .when('/about', {
                templateUrl : 'about.html',
                controller  : 'aboutController'
            })
        	.when('/create', {
            templateUrl : 'createReport/createReport.html',
            controller  : 'createController'
        })
        .when('/report', {
            templateUrl : 'report/report.html',
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
            
    });

    // create the controller and inject Angular's $scope
    scotchApp.controller('mainController', function($scope) {
        // create a message to display in our view
        $scope.message = 'Everyone come and see how good I look!';
    });
  scotchApp.controller('aboutController', function($scope) {
      $scope.message = 'Look! I am an about page.';
    });
  scotchApp.controller('createController', function($scope) {
      $scope.message = 'Look! I am an create page.';
    });
  scotchApp.controller('expenseController', function($scope) {
      $scope.message = 'Look! I am an expense page.';
    });