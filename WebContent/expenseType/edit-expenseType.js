angular.module('edit-expenseType', []).controller(
		'edit-expenseType',
		function($rootScope, $scope, $stateParams, $state, formlyVersion, expenseTypeService) {
			var id = ($stateParams.id) ? parseInt($stateParams.id) : 0;
			$rootScope.title = (id > 0) ? 'Edit ExpenseType' : 'Add ExpenseType';
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
				expenseTypeService.get(id).then(function(status) {
					vm.model = status.data;
				});
			}

			vm.fields = [ {
				key : 'value',
				type : 'input',
				templateOptions : {
					label : 'Name',
					placeholder : 'name',
					type : 'text',
					required : true,
					disabled : true
				}
			}, {
				key : 'taxPercent',
				type : 'input',
				templateOptions : {
					label : 'TaxPercent',
					placeholder : 'comment',
					type : 'number',
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
