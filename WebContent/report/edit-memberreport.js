angular
.module('edit-memberreport',
		[ 'formly', 'formlyBootstrap', 'ngAnimate', 'ngMessages' ])
		.constant('formlyExampleApiCheck', apiCheck())
		.run(
				function(formlyConfig, formlyValidationMessages) {
					formlyConfig.extras.errorExistsAndShouldBeVisibleExpression = 'fc.$touched || form.$submitted';

					formlyValidationMessages.addStringMessage('required',
					'This field is required');
				})
				.config(
						function config(formlyConfigProvider, formlyExampleApiCheck) {
							var unique = 1;
							
							formlyConfigProvider.removeWrapperByName('bootstrapLabel');
						    formlyConfigProvider.setWrapper({
						      name: 'bootstrapLabel',
						      templateUrl: 'templates/label-wrapper.html'
						    });
						    
						    // Replace formlyBootstrap input field type to implement read-only forms
						    formlyConfigProvider.setType({
						      name: 'input',
						      templateUrl: 'templates/input-template.html',
						      wrapper: ['bootstrapLabel', 'bootstrapHasError'],
						      overwriteOk: true
						    });

							formlyConfigProvider.setWrapper({
								name : 'validation',
								types : [ 'input' ],
								templateUrl : 'templates/error-messages.html'
							});

							formlyConfigProvider.setWrapper({
								name : 'panel',
								templateUrl : 'templates/panel.html'
							});

							formlyConfigProvider
							.setType({
								name : 'expenseSection',
								templateUrl : 'templates/expenseSection.html',
								controller : function($scope, $uibModal) {
									$scope.formOptions = {
											formState : $scope.formState
									};
									$scope.edit = edit;
									$scope.createOptions = createOptions;
									$scope.createFields = createFields;
									$scope.history = [];

									function createOptions(options) {
										options = angular.copy(options);
										options['formState']['readOnly'] = true;
										return options;
									}
									
									function createFields(fields) {
										fields = angular.copy(fields);
										fields.splice(0, 2); 
										return fields;
									}

									function edit(model, add) {
										var result = $uibModal
										.open({
											templateUrl : 'templates/expense-categories.html',
											controller : function(
													$uibModalInstance,
													formData) {
												var vm = this;

												// function assignment
												vm.ok = ok;
												vm.cancel = cancel;

												// variable assignment
												vm.formData = formData;
												//vm.originalFields = angular
												//.copy(vm.formData.fields);

												// function definition
												function ok() {
													//console.info(JSON.stringify(vm.formData.fields[0]));
													$uibModalInstance
													.close(vm.formData);
												}

												function cancel() {
													vm.formData.options
													.resetModel()
													$uibModalInstance
													.dismiss('cancel');
												};
											},
											controllerAs : 'vm',
											resolve : {
												formData : function() {
													return {
														fields : getFormFields(),
														model : model
													}
												}
											}
										}).result;

										if (add) {
											result.then(function(formData) {
												$scope.history.push(formData);
											})

										}
									}

									function getFormFields() {
										return [
										        {
										        	'key' : 'expenseCategory',
										        	'type' : 'select',
										        	'templateOptions' : {
										        		'label' : 'Category',
										        		'options' : [],
										        		'valueProp' : 'id',
										        		'labelProp' : 'name',
										        	},
										        	controller : function(
										        			$scope, DataService) {
										        		$scope.to.loading = DataService
										        		.sports()
										        		.then(
										        				function(
										        						response) {
										        					$scope.to.options = response;
										        					return response;
										        				});
										        	}
										        },
										        {
										        	'key' : 'expenseType',
										        	'type' : 'select',
										        	'templateOptions' : {
										        		'label' : 'Type',
										        		'options' : [],
										        		'valueProp' : 'id',
										        		'labelProp' : 'name'
										        	},
										        	watcher : {
										        		listener : function(
										        				field,
										        				newValue,
										        				oldValue,
										        				$scope,
										        				stopWatching) {
										        			$scope.model[$scope.options.key] = $scope.model[$scope.options.key]
										        			|| [];
										        			var repeatsection = $scope.fields;
										        			// console.info($scope);
										        			if (newValue == 2) {
										        				if (repeatsection.length > 2) {
										        					repeatsection
										        					.pop();
										        				}
										        				var newsection = {
										        						key: 'test2',
										        						type : 'input',
										        						templateOptions : {
															        		label : 'Category'
															        	}
										        				};
										        				repeatsection
										        				.push(newsection);
										        			} else if (newValue == 3) {
										        				if (repeatsection.length > 2) {
										        					repeatsection
										        					.pop();
										        				}
										        				var newsection = {
										        						key: 'test1',
										        						type : 'select'
										        				};
										        				repeatsection
										        				.push(newsection);
										        			}
										        		}
										        	},
										        	controller : function(
										        			$scope, DataService) {
										        		$scope
										        		.$watch(
										        				'model.expenseCategory',
										        				function(
										        						newValue,
										        						oldValue,
										        						theScope) {
										        					if (newValue !== oldValue) {
										        						if ($scope.model[$scope.options.key]
										        						&& oldValue) {
										        							$scope.model[$scope.options.key] = '';
										        						}
										        					}
										        					$scope.to.loading = DataService
										        					.teams(
										        							newValue)
										        							.then(
										        									function(
										        											response) {
										        										$scope.to.options = response;
										        									});

										        				});
										        	}
										        } ];
									}
								}
							});
						})
						.controller(
								'edit-memberreport',
								function($rootScope, $scope, $stateParams, $state,
										formlyVersion, memberReportService, $uibModal) {
									var id = ($stateParams.id) ? parseInt($stateParams.id) : 0;
									$rootScope.title = (id > 0) ? 'Edit Report' : 'Add Report';
									$rootScope.buttonText = (id > 0) ? 'Update' : 'Add';
									$scope.types = [];
									var vm = this;
									vm.options = {};
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

									if (id == 0) {
										vm.model = {};
									} else {
										memberReportService.get(id).then(function(status) {
											vm.model = status.data;
										});
									}

									vm.fields = [ {
										key : 'reason',
										type : 'textarea',
										templateOptions : {
											label : 'Reason',
											placeholder : 'Reason',
										}
									}, {
										type : 'expenseSection',
										key : 'expenses',
										wrapper : 'panel',
										templateOptions : {
											label : 'Expense',
											btnText : 'Add',
											fields : []
										}
									} ];

									function onSubmit() {
										if (vm.form.$valid) {
											if (id > 0) {
												memberReportService
												.update(id, vm.model)
												.then(
														function(status) {
															$state
															.go('dashboard.list-memberreports');
														});
											} else {
												memberReportService
												.insert(vm.model)
												.then(
														function(status) {
															$state
															.go('dashboard.list-memberreports');
														});
											}

										}
									}

								}).factory('DataService', function DataService($http, $q) {
									return {
										sports : sports,
										teams : teams,
										players : players
									};

									function sports() {
										var deferred = $q.defer();
										// dummy data
										var data = [ {
											id : 1,
											name : 'Soccer'
										}, {
											id : 2,
											name : 'Basketball'
										} ];
										deferred.resolve(data);
										return deferred.promise;
									}

									function teams(sport_id) {
										var deferred = $q.defer();
										// dummy data
										var data = [ {
											id : 1,
											fk : 1,
											name : 'Bayern Munich'
										}, {
											id : 2,
											fk : 1,
											name : 'Real Madrid'
										}, {
											id : 3,
											fk : 2,
											name : 'Cleveland'
										} ];
										if (!!sport_id) {
											var tmp = [];
											angular.forEach(data, function(val) {
												if (val.fk === sport_id)
													tmp.push(val);
											});
											deferred.resolve(tmp);
										} else {
											deferred.resolve(data);
										}

										return deferred.promise;
									}

									function players(team_id) {
										var deferred = $q.defer();
										// dummy data
										var data = [ {
											id : 1,
											fk : 1,
											name : 'Mario Götze'
										}, {
											id : 1,
											fk : 2,
											name : 'Javier Hernandez'
										}, {
											id : 2,
											fk : 3,
											name : 'LeBron James'
										} ];
										if (!!team_id) {
											var tmp = [];
											angular.forEach(data, function(val) {
												if (val.fk === team_id)
													tmp.push(val);
											});
											deferred.resolve(tmp);
										} else {
											deferred.resolve(data);
										}

										return deferred.promise;
									}

								});
