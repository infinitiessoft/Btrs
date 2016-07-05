angular
.module('list-memberaudits', [])
.controller(
		'list-memberaudits',
		[
		 '$scope',
		 '$http',
		 '$stateParams',
		 'memberAuditService',
		 function($scope, $http, $stateParams, memberAuditService) {
			 $scope.currentStatus = $stateParams.currentStatus;
			 $scope.startDate = $stateParams.startDate;
			 $scope.endDate = $stateParams.endDate;
			 $scope.userId = $stateParams.userid;
			 var userId = $stateParams.userid;
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
				 memberAuditService
				 .list(userId, filters)
				 .then(
						 function(status) {
							
							 $scope.displayed = status.data.content;
							 tableState.pagination.numberOfPages = status.data.totalPages;
							 $scope.isLoading = false;
							 $scope.size = status.data.totalElements
						 });
			 };

			 $scope.removeEntry = function(newsEntry) {
				 if (confirm("Are you sure to delete report: "
						 + newsEntry.id) == true) {
					 $scope.isLoading = true;
					 memberAuditService
					 .remove(userId, newsEntry.id)
					 .then(
							 function(status) {
								 var filters = queryParams(lastState);

								 memberAuditService
								 .list(userId, filters)
								 .then(
										 function(
												 status) {
											 $scope.displayed = status.data.content;
											 $scope.isLoading = false;
										 });
							 });

				 }
			 };

		 } ]);
