angular
		.module('edit-user', [ 'formly', 'formlyBootstrap' ])
		.config(function config(formlyConfigProvider, formlyExampleApiCheck) {
			formlyConfigProvider.removeWrapperByName('bootstrapLabel');
			formlyConfigProvider.setWrapper({
				name : 'bootstrapLabel',
				templateUrl : 'templates/label-wrapper.html'
			});

			// Replace formlyBootstrap input field type to implement
			// read-only forms
			formlyConfigProvider.setType({
				name : 'input',
				templateUrl : 'templates/input-template.html',
				wrapper : [ 'bootstrapLabel', 'bootstrapHasError' ],
				overwriteOk : true
			});
		})
		.constant('formlyExampleApiCheck', apiCheck())
		.controller(
				'edit-user',
				function($rootScope, $scope, $stateParams, $state,
						formlyVersion, userService, jobTitleService, userRoleService) {
					var id = parseInt($stateParams.id);
					$rootScope.title = 'Edit User';
					$rootScope.buttonText = 'Update';
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

					vm.model = {
						jobTitle : {}
					};

					vm.origRoles = {};
					vm.roles = {1:false,2:false,3:false};

					userService.get(id).then(function(status) {
						for ( var i in status.data) {
							if (i == 'jobTitle') {
								continue;
							}
							vm.model[i] = status.data[i];
						}
						
						angular
						.forEach(
								vm.model.roles,
								function(role) {
									vm.roles[role.id] = true;
									vm.origRoles[role.id] = true;
								});

						vm.model.jobTitle.id = status.data.jobTitle.id;
					});

					vm.fields = [
							{
								className : 'row',
								fieldGroup : [
										{
											key : 'name',
											type : 'input',
											templateOptions : {
												label : 'Name',
												placeholder : 'name',
												type : 'text',
												disabled : true
											}
										},
										{
											key : 'username',
											type : 'input',
											templateOptions : {
												label : 'Username',
												placeholder : 'username',
												type : 'text',
												disabled : true
											}
										},
										{
											key : 'email',
											type : 'input',
											templateOptions : {
												label : 'E-mail',
												placeholder : 'email',
												type : 'text',
												disabled : true
											}
										},
										{
											key : 'gender',
											type : 'input',
											templateOptions : {
												label : 'Gender',
												placeholder : 'gender',
												type : 'text',
												disabled : true
											}
										},
										{
											key : 'dateofjoined',
											type : 'input',
											templateOptions : {
												label : 'Joined Date',
												placeholder : 'dateofjoined',
												type : 'text',
												disabled : true
											}
										},
										{
											key : 'id',
											type : 'select',
											wrapper : 'loading',
											model : vm.model.jobTitle,
											templateOptions : {
												label : 'Job Title',
												options : [],
												valueProp : 'id',
												labelProp : 'name',
												required : true,
												placeholder : 'Select Job Title from List'
											},
											controller : /* @ngInject */function(
													$scope, jobTitleService) {
												$scope.to.loading = jobTitleService
														.list()
														.then(
																function(
																		response) {
																	$scope.to.options = response.data.content;
																	// note, the
																	// line
																	// above
																	// is
																	// shorthand
																	// for:
																	// $scope.options.templateOptions.options
																	// = data;
																	return response;
																});
											}
										} ]
							},{
								template : '<hr />'
							}, {
								key : 'roles',
								wrapper : 'panel',
								templateOptions : {
									label : 'Role'
								},
								fieldGroup : [ {
									"type" : "checkbox",
									"key" : "3",
									'model' : vm.roles,
									"templateOptions" : {
										"label" : "Admin"
									}
								}, {
									"type" : "checkbox",
									"key" : "2",
									'model' : vm.roles,
									"templateOptions" : {
										"label" : "Accountant"
									}
								}, {
									"type" : "checkbox",
									"key" : "1",
									'model' : vm.roles,
									"templateOptions" : {
										"label" : "Employee"
									}
								} ]
							} ];
					function onSubmit() {
						if (vm.form.$valid) {
							userService.update(id, vm.model).then(
									function(status) {
										for ( var i in vm.roles) {
											if(!vm.origRoles[i] && vm.roles[i]){
												userRoleService.put(id,i);
											}
											else if(vm.origRoles[i] && !vm.roles[i]){
												userRoleService.remove(id,i);
											}
										}
										$state.go('dashboard.list-users');
									});
						}
					}
				});
