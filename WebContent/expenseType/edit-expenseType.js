angular.module('edit-expenseType', []).controller(
		'edit-expenseType',
		function($rootScope, $scope, $stateParams, $state, $translate, formlyVersion, expenseTypeService) {
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
			
			vm.options = {formState : {}};
			
			Object.defineProperty(vm.options.formState, 'lang', {
				enumerable : true,
				get : function() {
					console.log('getting ' + $translate.use());
					return $translate.use();
				},
				set : function(arg) {
					console.log('setting ' + arg);
					return $translate.use(arg);
				}
			});


			if (id == 0) {
				vm.model = {};
			} else {
				expenseTypeService.get(id).then(function(status) {
					vm.model = status.data;
					vm.model.value = $translate.instant(status.data.value);
				});
			}

			vm.fields = [ {
				key : 'value',
				type : 'input',
				templateOptions : {
					label : $translate.instant('Name'),
					placeholder : 'name',
					type : 'text',
					required : true,
					disabled : true
				}
			}, {
				key : 'taxPercent',
				type : 'input',
				templateOptions : {
					label : $translate.instant('TaxPercent'),
					placeholder : $translate.instant('tax percent'),
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
