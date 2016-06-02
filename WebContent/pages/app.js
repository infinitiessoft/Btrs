var app = angular.module("app", []);
    app.controller("accountController", function ($scope, $http) {
        
    $scope.keyword = '';
    $scope.form = {
    		username: "",
			   email:"",
			   password: "",
			   password: "",
			   firstName: "",
			   lastName: "",
			   jobTitle: "",
    };
    
    $scope.GetAllData = function () {
        $http.get('/rest/userShared:id', {id: '@id'})
        .success(function (data,status,headers,config){
        	$scope.Details = data;
        	})
        	.error(function (data, status, header, config) {
                $scope.ResponseDetails = "Data: " + data +
                    "<br />status: " + status +
                    "<br />headers: " + jsonFilter(header) +
                    "<br />config: " + jsonFilter(config);
            });
        };
       function addAccount( username,email,password,password,firstName,lastName,jobTitle ) {
    	   var request = $http({
    		   method: "put",
    		   url: "rest/userShared",
    		   params: {
    			   action: "add"
    		   },
    		   data: {
    			   username: username,
    			   email: email,
    			   password: password,
    			   password: password,
    			   firstName: firstName,
    			   lastName: lastName,
    			   jobTitle: jobTitle,
    		   }
    	   });
    	   return(request.then(handleSuccess,handleError));

       }
       function handleError(response){
    	   if(
    			   !angular.isObject(reponse.data)||
    			   ! response.data.message){
    		   return($scope.reject("An unknow error occurred"));
    	   }
    	   return($scope.reject(response.data.message));
       }
       function handleSuccess(response){
    	   return(response.data);
       }
       
    });
       
 

        
