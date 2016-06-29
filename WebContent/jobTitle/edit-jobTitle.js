angular.module('edit-jobTitle', []).controller(
		'edit-jobTitle',
		function($rootScope, $scope, $stateParams, $state, formlyVersion, jobTitleService) {
			var id = ($stateParams.id) ? parseInt($stateParams.id) : 0;
			$rootScope.title = (id > 0) ? 'Edit JobTitle' : 'Add JobTitle';
			$rootScope.buttonText = (id > 0) ? 'Update' : 'Add';
			var vm = this;
			$scope.vm = vm;
			vm.onSubmit = onSubmit;
			vm.author = {
				name : 'pohsun',
				url : ''
			};
			vm.env = {
				angularVersion : angular.version.full,
				formlyVersion : formlyVersion
			};

			vm.options = {
				formState : {
					horizontalLabelClass : 'col-sm-2',
					horizontalFieldClass : 'col-sm-10',
					readOnly : true
				}
			};

			if (id == 0) {
				vm.model = {};
			} else {
				jobTitleService.get(id).then(function(status) {
					vm.model = status.data;
				});
			}

			vm.fields = [ {
				key : 'name',
				type : 'input',
				templateOptions : {
					label : 'Name',
					placeholder : 'name',
					type : 'text',
					required : true
				}
			}, {
				key : 'comment',
				type : 'input',
				templateOptions : {
					label : 'Comment',
					placeholder : 'comment',
					type : 'text',
					required : true
				}
			} ];
			function onSubmit() {
				if (vm.form.$valid) {
					if (id > 0) {
						jobTitleService.update(id, vm.model).then(
								function(status) {
									$state.go('dashboard.list-jobTitles');
								});
					} else {
						jobTitleService.insert(vm.model).then(function(status) {
							$state.go('dashboard.list-jobTitles');
						});
					}
				}
			}
		});
