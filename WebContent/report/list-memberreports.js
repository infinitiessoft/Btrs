angular
.module('list-memberreports', [])
.controller(
		'list-memberreports',
		[
		 '$scope',
		 '$http',
		 '$stateParams',
		 'memberReportService',
		 function($scope, $http, $stateParams, memberReportService) {
			 $scope.currentStatus = $stateParams.currentStatus;
			 $scope.startDate = $stateParams.startDate;
			 $scope.endDate = $stateParams.endDate;
			 var lastState = {
					 pagination : {
						 start : 0,
						 number : 10
					 },
					 sort : {
						 predicate : 'id',
						 reverse : true
					 },
					 search : {
						 predicateObject : {}
					 }
			 };

			 var queryParams = function(tableState) {
				 var pagination = tableState.pagination;
				 var start = pagination.start || 0;
				 var pageSize = pagination.number || 10;
				 var sort = tableState.sort.predicate;
				 var dir = tableState.sort.reverse ? 'DESC'
						 : 'ASC';
				 var predicate = tableState.search.predicateObject;
				 var page = (start / pageSize);
				 if (page < 0) {
					 page = 0;
				 }
				 lastState.pagination.start = start;
				 lastState.pagination.number = pageSize;
				 lastState.sort.predicate = sort;
				 lastState.sort.reverse = tableState.sort.reverse;
				 lastState.search = tableState.search;
				 var filters = tableState.search.predicateObject
				 || {};
				 filters.sort = sort;
				 filters.pageSize = pageSize;
				 filters.page = page;
				 filters.dir = dir;

				 return filters;
			 }

			 $scope.displayed = [];
			 $scope.callServer = function callServer(tableState) {
				 $scope.isLoading = true;
				 var filters = queryParams(tableState);
				 memberReportService
				 .list(filters)
				 .then(
						 function(status) {
							
							 $scope.displayed = status.data.content;
							 tableState.pagination.numberOfPages = status.data.totalPages;
							 $scope.isLoading = false;
						 });
			 };

			 $scope.removeEntry = function(newsEntry) {
				 if (confirm("Are you sure to delete report: "
						 + newsEntry.id) == true) {
					 $scope.isLoading = true;
					 memberReportService
					 .remove(newsEntry.id)
					 .then(
							 function(status) {
								 var filters = queryParams(lastState);

								 memberReportService
								 .list(filters)
								 .then(
										 function(
												 status) {
											 $scope.displayed = status.data.content;
											 $scope.isLoading = false;
										 });
							 });

				 }
			 };

		 } ])
		 .factory(
				 'memberReportService',
				 [
				  'auth','$http',
				  function(auth, $http) {
					  var serviceBase = 'rest/v1.0/users/';
					  var obj = {};
					  obj.list = function(queries) {
						  var url  = serviceBase + auth.user.principal.id + '/reports/';
						  return $http.get(url, {params:queries});
					  };

					  obj.get = function(id) {
						  var url  = serviceBase + auth.user.principal.id + '/reports/';
						  return $http.get(url  + id);
					  };

					  obj.insert = function(record) {
						  var url  = serviceBase + auth.user.principal.id + '/reports/';
						  return $http.post(url, record);
					  };

					  obj.update = function(id, record) {
						  var url  = serviceBase + auth.user.principal.id + '/reports/';
						  return $http.put(url  + id,
								  record);
					  };

					  obj.remove = function(id) {
						  var url  = serviceBase + auth.user.principal.id + '/reports/';
						  return $http.delete(url + id);
					  };

					  return obj;
				  } ]);
