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
								name : 'parameterSection',
								templateUrl : 'templates/parameterSection.html',
								controller : function($scope) {
									$scope.formOptions = {
										formState : $scope.formState
									};

									$scope.createFields = createFields;

									function createFields(model) {
										var fields = [];
										var typeParameter = model.typeParameter;
										var field = createField(
												'value',
												typeParameter.value,
												typeParameter.dataType);
										fields.push(field);
										addRandomIds(fields);
										return fields;
									}
									
									function createField(key, label, dataType) {
										return {
											key : key,
											type : 'input',
											templateOptions : {
												label : label,
												type : dataType,
												required : true,

											}
										};
									}

									function addRandomIds(fields) {
										unique++;
										angular
												.forEach(
														fields,
														function(field, index) {
															if (field.fieldGroup) {
																addRandomIds(field.fieldGroup);
																return; // fieldGroups
																// don't
																// need
																// an ID
															}

															if (field.templateOptions
																	&& field.templateOptions.fields) {
																addRandomIds(field.templateOptions.fields);
															}

															field.id = field.id
																	|| (field.key
																			+ '_'
																			+ index
																			+ '_'
																			+ unique + getRandomInt(
																			0,
																			9999));
														});
									}

									function getRandomInt(min, max) {
										return Math.floor(Math.random()
												* (max - min))
												+ min;
									}
								}
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
									$scope.expenseTypes = $scope.to.expenseTypes;

									function createOptions() {
										var options = {
											formState : {}
										};
										options['formState']['readOnly'] = true;
										return options;
									}

									function createFields(model) {
										var expenseType = model.expenseType;
										var fields = [];
										var  parameterSection = {
												type : 'parameterSection',
												key : 'parameterValues',
												templateOptions : {
													label : 'Parameter',
													'fields' : []
												}
											};
										fields.push({
											key : 'value',
											type : 'input',
											'model' : 'model.expenseType',
											templateOptions : {
												label : 'ExpenseType',
												disabled : 'true'
											}
										});
										fields.push(parameterSection);
										addRandomIds(fields);
										return fields;
									}

									function createFieldsById($scope, expenseType) {
										$scope.model["parameterValues"] = $scope.model["parameterValues"]
												|| [];
										var repeatsection = $scope.model["parameterValues"];

										var typeParameters = expenseType.typeParameters;
										var fields = [];
										angular
												.forEach(
														typeParameters,
														function(typeParameter,
																index) {
															repeatsection.push({'typeParameter':typeParameter});
														});
										addRandomIds(fields);

										var parameterSection = {
											type : 'parameterSection',
											key : 'parameterValues',
											templateOptions : {
												label : 'Parameter',
												'fields' : []
											}
										};

										return [ parameterSection ];
									}

									function addRandomIds(fields) {
										unique++;
										angular
												.forEach(
														fields,
														function(field, index) {
															if (field.fieldGroup) {
																addRandomIds(field.fieldGroup);
																return; // fieldGroups
																// don't
																// need
																// an ID
															}

															if (field.templateOptions
																	&& field.templateOptions.fields) {
																addRandomIds(field.templateOptions.fields);
															}

															field.id = field.id
																	|| (field.key
																			+ '_'
																			+ index
																			+ '_'
																			+ unique + getRandomInt(
																			0,
																			9999));
														});
									}

									function getRandomInt(min, max) {
										return Math.floor(Math.random()
												* (max - min))
												+ min;
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
														// vm.originalFields =
														// angular
														// .copy(vm.formData.fields);

														// function definition
														function ok() {
															$uibModalInstance
																	.close(vm.formData);
														}

														function cancel() {
															vm.formData.options
																	.resetModel()
															$uibModalInstance
																	.dismiss('cancel');
														}
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
											result
													.then(function(formData) {
														$scope.model[$scope.options.key] = $scope.model[$scope.options.key]
																|| [];
														var repeatsection = $scope.model[$scope.options.key];
														repeatsection
																.push(formData.model);
													})

										}
									}

									function getFormFields() {
										return [ {
											'key' : 'expenseType',
											'type' : 'select',
											'templateOptions' : {
												'label' : 'Type',
												'options' : $scope.expenseTypes,
												'ngOptions' : 'option as option.value group by option.expenseCategory.name for option in to.options'
											},
											watcher : {
												listener : function(field,
														newValue, oldValue,
														$scope, stopWatching) {
													var repeatsection = $scope.fields;
													if (!newValue)
														return;
													if (newValue.id == 1) {
														while (repeatsection.length > 1) {
															repeatsection.pop();
														}

														
														var newsections = createFieldsById($scope, newValue);
														angular
																.forEach(
																		newsections,
																		function(
																				newsection) {
																			repeatsection
																					.push(newsection);
																		});
													} else if (newValue.id == 2) {
														while (repeatsection.length > 1) {
															repeatsection.pop();
														}
														
														var newsections = createFieldsById($scope, newValue);
														angular
																.forEach(
																		newsections,
																		function(
																				newsection) {
																			repeatsection
																					.push(newsection);
																		});
													}
												}
											}
										} ];
									}
								}
							});
				}).controller(
				'edit-memberreport',
				function($rootScope, $scope, $stateParams, $state,
						formlyVersion, $uibModal, memberReportService,
						memberExpenseTypeService) {
					var id = ($stateParams.id) ? parseInt($stateParams.id) : 0;
					$rootScope.title = (id > 0) ? 'Edit Report' : 'Add Report';
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

					if (id == 0) {
						vm.model = {};
					} else {
						memberReportService.get(id).then(function(status) {
							vm.model = status.data;
						});
					}

					vm.expenseTypes = [];
					memberExpenseTypeService.list().then(function(response) {
						angular.forEach(response.data.content, function(val) {
							vm.expenseTypes.push(val);
						});
					});
					vm.attendRecords = [];

					vm.fields = [ {
						key : 'attendanceRecordId',
						type : 'select',
						templateOptions : {
							label : 'AttendRecord',
							options:vm.attendRecords,
							"required": true
						}
					},{
						key : 'reason',
						type : 'textarea',
						templateOptions : {
							label : 'Reason',
							placeholder : 'Reason',
							"required": true
						}
					}, {
						key : 'route',
						type : 'textarea',
						templateOptions : {
							label : 'Route',
							placeholder : 'Route',
							"required": true
						}
					}, {
						key : 'comment',
						type : 'textarea',
						templateOptions : {
							label : 'Comment',
							placeholder : 'Comment',
						}
					}, {
						type : 'expenseSection',
						key : 'expenses',
						wrapper : 'panel',
						templateOptions : {
							label : 'Expense',
							btnText : 'Add',
							expenseTypes : vm.expenseTypes
						}
					} ];

					function onSubmit() {
						console.info('submit' + JSON.stringify(vm.model));
						// if (vm.form.$valid) {
						// if (id > 0) {
						// memberReportService
						// .update(id, vm.model)
						// .then(
						// function(status) {
						// $state
						// .go('dashboard.list-memberreports');
						// });
						// } else {
						// memberReportService
						// .insert(vm.model)
						// .then(
						// function(status) {
						// $state
						// .go('dashboard.list-memberreports');
						// });
						// }
						//
						// }
					}

				});
