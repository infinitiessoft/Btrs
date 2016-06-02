 var formExample = angular.module('formExample', ['ngRoute']);

    // configure our routes
 formExample.config(function($routeProvider) {
        $routeProvider

            
        .when('/', {
            templateUrl : 'expense/expense.html',
            controller  : 'expenseController'
        })

        .when('/photo', {
            templateUrl : 'expense/photo.html',
            controller  : 'photoController'
        });
            
    });

    // create the controller and inject Angular's $scope
 formExample.controller('expenseController', [ '$scope', '$resource',
                                    			function($scope, $resource) {
		$scope.saveExpenses = function() {
			$scope.users.push({'expenseCategory':$scope.expenseCategory, 'expenseType':$scope.expenseType, 'comment': $scope.comment, 'numberOfPerson':$scope.numberOfPerson, 'numberOfDays':$scope.numberOfDays, 'amount': $scope.amount, 'photoUpload': $scope.photoUpload});
			}
		var Expense = $resource('rest/expense/');
		Expense.save({expenseCategory:$scope.expenseCategory, expenseType:$scope.expenseType, comment: $scope.comment, numberOfPerson:$scope.numberOfPerson, numberOfDays:$scope.numberOfDays, amount: $scope.amount, uploadPhoto: $scope.uploadPhoto}, function(response){
			$scope.message = response.message;
		});
		$scope.expenseCategory='';
		$scope.expenseType='';
		$scope.comment='';                     					
		$scope.numberOfDays='';
		$scope.amount='';  
		$scope.uploadPhoto='';
 }
 ]);                           				
                             				