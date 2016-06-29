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

					formlyConfigProvider
							.setType({
								name : 'reportParameterSection',
								templateUrl : 'templates/parameterSection.html',
								overwriteOk : true,
								controller : function($scope) {
									$scope.formOptions = {
										formState : $scope.formState
									};

									$scope.createFields = createFields;

									function createFields(model) {
										var fields = [];
										var typeParameter = model.typeParameter;
										var field = createField('value',
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
								name : 'reportExpenseSection',
								templateUrl : 'templates/expenseSection.html',
								overwriteOk : true,
								controller : function($scope, $uibModal,
										memberExpenseTypeService) {
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
										var parameterSection = {
											type : 'reportParameterSection',
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

									function createFields(model) {
										var expenseTypeId = model.expenseType.id;
										var fields = [];

										var row = {
											className : 'row',
											fieldGroup : [ {
												className : 'col-xs-2',
												key : 'value',
												type : 'input',
												'model' : 'model.expenseType',
												templateOptions : {
													label : 'ExpenseType',
													disabled : 'true'
												}
											}, {
												className : 'col-xs-8',
												type : 'reportParameterSection',
												key : 'parameterValues',
												templateOptions : {
													label : 'Parameter',
													'fields' : []
												}
											}, {
												className : 'col-xs-2',
												key : 'totalAmount',
												type : 'input',
												templateOptions : {
													label : 'Total amount',
													type : 'number',
													required : true,
												}
											} ]
										};

										fields.push(row);

										// fields.push(parameterSection);

										addRandomIds(fields);
										return fields;
									}

									function createFieldsById($scope,
											expenseType) {
										$scope.model["parameterValues"] = [];
										
										var repeatsection = $scope.model["parameterValues"];
										var typeParameters = expenseType.typeParameters;
										var fields = [];
										angular
												.forEach(
														typeParameters,
														function(typeParameter,
																index) {
															repeatsection
																	.push({
																		'typeParameter' : typeParameter
																	});
														});
										addRandomIds(fields);

										var parameterSection = {
											type : 'reportParameterSection',
											key : 'parameterValues',
											templateOptions : {
												label : 'Parameter',
												'fields' : []
											}
										};
										
										var totalAmount = {
								            	  key : 'totalAmount',
								            	  type : 'input',
								            	  templateOptions : {
								            		  label : 'Total amount',
								            		  type : 'number',
								            		  required : true,
								            	  }
								              };

										return [ parameterSection, totalAmount ];
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
														while (repeatsection.length > 1) {
															repeatsection.pop();
														}

														var newsections = createFieldsById(
																$scope,
																newValue);
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
										} ];
									}
								}
							});
				})
		.controller(
				'edit-memberreport',
				function($rootScope, $scope, $stateParams, $state,
						formlyVersion, $uibModal, memberReportService,
						memberExpenseTypeService, memberRecordService) {
					var id = ($stateParams.reportid) ? parseInt($stateParams.reportid)
							: 0;
					var recordId = ($stateParams.recordid) ? parseInt($stateParams.recordid)
							: 0;
					var userId = $stateParams.userid;

					$rootScope.title = (id > 0) ? 'Edit Report' : 'Add Report';
					$rootScope.buttonText = (id > 0) ? 'Update' : 'Add';
					var vm = this;
					vm.recordModel = {};

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
						memberReportService.get(userId, id).then(
								function(status) {
									vm.model = status.data;
								});
					}

					memberRecordService
							.get(userId, recordId)
							.then(
									function(status) {
										vm.model.attendanceRecordId = status.data.id;
										if (id == 0) {
											vm.model.reason = status.data.reason;
										}
										vm.recordModel.reason = status.data.reason;
										vm.recordModel.duration = status.data.duration;
										vm.recordModel.startDate = status.data.startDate;
										vm.recordModel.endDate = status.data.endDate;
										vm.recordModel.applicant = status.data.applicant;
									});

					vm.expenseTypes = [];
					memberExpenseTypeService.list().then(function(response) {
						angular.forEach(response.data.content, function(val) {
							vm.expenseTypes.push(val);
						});
					});
					vm.attendRecords = [];

					vm.fields = [ {
						className : 'section-label',
						template : '<div><strong>Attend Record:</strong></div>'
					}, {
						className : 'row',
						fieldGroup : [ {
							className : 'col-xs-6',
							type : 'input',
							key : 'applicant.name',
							model : vm.recordModel,
							templateOptions : {
								label : 'Applicant',
								disabled : true
							}
						} ]
					}, {
						className : 'row',
						fieldGroup : [ {
							className : 'col-xs-6',
							type : 'textarea',
							key : 'reason',
							model : vm.recordModel,
							templateOptions : {
								label : 'Reason',
								disabled : true
							}
						} ]
					}, {
						className : 'row',
						fieldGroup : [ {
							className : 'col-xs-2',
							type : 'input',
							key : 'startDate',
							model : vm.recordModel,
							templateOptions : {
								label : 'Start Date',
								disabled : true
							}
						}, {
							className : 'col-xs-2',
							type : 'input',
							key : 'endDate',
							model : vm.recordModel,
							templateOptions : {
								label : 'EndDate',
								disabled : true
							}
						}, {
							className : 'col-xs-2',
							type : 'input',
							key : 'duration',
							model : vm.recordModel,
							templateOptions : {
								label : 'Duration',
								disabled : true
							}
						} ]
					}, {
						template : '<hr />'
					}, {
						className : 'row',
						fieldGroup : [ {
							className : 'col-xs-4',
							key : 'firmOrProject',
							type : 'input',
							templateOptions : {
								label : 'Firm/Project',
								placeholder : 'Firm/Project',
								"required" : false
							}
						} ]
					}, {
						className : 'row',
						fieldGroup : [ {
							className : 'col-xs-4',
							key : 'route',
							type : 'textarea',
							templateOptions : {
								label : 'Route',
								placeholder : 'Route',
								"required" : true
							}
						} ]
					}, {
						className : 'row',
						fieldGroup : [ {
							className : 'col-xs-4',
							key : 'comment',
							type : 'textarea',
							templateOptions : {
								label : 'Comment',
								placeholder : 'Comment',
							}
						} ]
					}, {
						className : 'row',
						fieldGroup : [ {
							className : 'col-xs-8',
							type : 'reportExpenseSection',
							key : 'expenses',
							wrapper : 'panel',
							templateOptions : {
								label : 'Expense',
								btnText : 'Add',
								expenseTypes : vm.expenseTypes,
							}
						} ]
					} ];

					function onSubmit() {
						if (vm.model.expenses)
							angular
									.forEach(
											vm.model.expenses,
											function(expense) {
												delete expense.expenseType.taxPercent;
												delete expense.expenseType.value;
												delete expense.expenseType.expenseCategory;
												delete expense.expenseType.typeParameters;
											});
						if (vm.form.$valid) {
							if (id > 0) {
								memberReportService
										.update(userId, id, vm.model)
										.then(
												function(status) {
													$state
															.go(
																	'dashboard.list-memberreports',
																	{
																		userid : userId
																	});
												});
							} else {
								memberReportService
										.insert(userId, vm.model)
										.then(
												function(status) {
													$state
															.go(
																	'dashboard.list-memberreports',
																	{
																		userid : userId
																	});
												});
							}

						}
					}

				});
