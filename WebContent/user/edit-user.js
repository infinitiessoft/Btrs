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
				function($rootScope, $scope, $stateParams, $state, $translate,
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
					
//					Object.defineProperty(vm.options.formState, 'lang', {
//						enumerable : true,
//						get : function() {
//							console.log('getting ' + $translate.use());
//							return $translate.use();
//						},
//						set : function(arg) {
//							console.log('setting ' + arg);
//							return $translate.use(arg);
//						}
//					});

					vm.origRoles = {};
					vm.roles = {90:false,91:false,92:false};

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
						if(vm.model.gender){
							vm.model.gender = $translate.instant(vm.model.gender);
						}
					});

					vm.fields = [
							{
								className : 'row',
								fieldGroup : [
										{
											key : 'name',
											type : 'input',
											templateOptions : {
												label : $translate.instant('Name'),
												placeholder : 'name',
												type : 'text',
												disabled : true
											}
										},
										{
											key : 'username',
											type : 'input',
											templateOptions : {
												label : $translate.instant('Username'),
												placeholder : 'username',
												type : 'text',
												disabled : true
											}
										},
										{
											key : 'email',
											type : 'input',
											templateOptions : {
												label : $translate.instant('Email'),
												placeholder : 'email',
												type : 'text',
												disabled : true
											}
										},
										{
											key : 'gender',
											type : 'input',
											templateOptions : {
												label : $translate.instant('Gender'),
												placeholder : 'gender',
												type : 'text',
												disabled : true
											}
										},
										{
											key : 'dateofjoined',
											type : 'input',
											templateOptions : {
												label : $translate.instant('Created Date'),
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
												label : $translate.instant('Job Title'),
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
																	angular
																	.forEach(
																			response.data.content,
																			function(job) {
																				job.name = $translate.instant(job.name);
																			});
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
									label : $translate.instant('Roles')
								},
								fieldGroup : [ {
									"type" : "checkbox",
									"key" : "92",
									'model' : vm.roles,
									"templateOptions" : {
										"label" : $translate.instant("ADMIN")
									}
								}, {
									"type" : "checkbox",
									"key" : "91",
									'model' : vm.roles,
									"templateOptions" : {
										"label" : $translate.instant("ACCOUNTANT")
									}
								}, {
									"type" : "checkbox",
									"key" : "90",
									'model' : vm.roles,
									"templateOptions" : {
										"label" : $translate.instant("EMPLOYEE")
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
