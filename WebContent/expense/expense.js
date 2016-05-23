 var formExample = angular.module('formExample', ['ngRoute']);

    // configure our routes
 formExample.config(function($routeProvider) {
        $routeProvider

            // route for the home page
        .when('/', {
            templateUrl : 'expense/expense.html',
            controller  : 'expenseController'
        })

        // route for the about page
        .when('/photo', {
            templateUrl : 'expense/photo.html',
            controller  : 'photoController'
        });
            
    });

    // create the controller and inject Angular's $scope
 formExample.controller('photoController', function($scope) {
        // create a message to display in our view
        $scope.message = 'Everyone come and see how good I look!';
    });