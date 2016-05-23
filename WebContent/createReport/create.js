var app = angular.module("formExample", []);
   app.controller('ExampleController', function($scope,$http) {
      $scope.SendData = function(){
    	  var data = $.param({
    		  Accountant: $scope.accountant,
    		  LeaveRecord:$scope.leaveRecord,
    		  Route:$scope.route,
    		  Reason:$scope.reason,
    		  StartDate:$scope.startDate,
    		  EndDate:$scope.endDate,
    		  Comment:$scope.comment
    	  });
      var config = {
    		  headers :{
    			  'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
    		  }
      }
      $http.post('rest/report',data,config)
      .success(function (data,status,headers,config){
    	  $scope.PostDataResponse = data;
    	  })
    	  .error(function (data,status,header,config){
    		  $scope.ResponseDetails = "Data:" + data +
    		"<hr />status: "+ status +
    		"<hr />headers: " + header +
    		"<hr />config: " + config;
    	  });
    	  };
      });
      