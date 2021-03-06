angular
		.module('edit-memberaudit',
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
								name : 'auditParameterSection',
								templateUrl : 'templates/parameterSection.html',
								overwriteOk : true,
								controller : function($scope,$translate) {
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
												label : $translate.instant(label),
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
								name : 'auditExpenseSection',
								templateUrl : 'templates/expenseSection.html',
								overwriteOk : true,
								controller : function($scope, $uibModal, $translate,
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
											type : 'auditParameterSection',
											key : 'parameterValues',
											templateOptions : {
												label : $translate.instant('Parameter'),
												'fields' : []
											}
										};
										fields.push({
											key : 'value',
											type : 'input',
											'model' : 'model.expenseType',
											templateOptions : {
												label : $translate.instant('ExpenseType'),
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
										var taxPercent = 0;

										memberExpenseTypeService
												.get(expenseTypeId)
												.then(
														function(response) {
															taxPercent = parseFloat(response.data.taxPercent);
															// var totalAmount =
															// model.totalAmount
															// ?
															// model.totalAmount
															// : 0;
															// model.taxAmount =
															// Math
															// .round(taxPercent
															// * totalAmount
															// / 100);
														});

										var row = {
											className : 'row',
											fieldGroup : [
													{
														className : 'col-xs-2',
														key : 'value',
														type : 'input',
														'model' : 'model.expenseType',
														templateOptions : {
															label : $translate.instant('ExpenseType'),
															disabled : 'true'
														}
													},
													{
														className : 'col-xs-6',
														type : 'auditParameterSection',
														key : 'parameterValues',
														templateOptions : {
															label : $translate.instant('Parameter'),
															'fields' : []
														}
													},
													{
														className : 'col-xs-2',
														key : 'totalAmount',
														type : 'input',
														templateOptions : {
															label : $translate.instant('Total amount'),
															type : 'number',
															required : true,
														}
													},
													{
														className : 'col-xs-2',
														key : 'taxAmount',
														type : 'input',
														templateOptions : {
															label : $translate.instant('Tax'),
															type : 'number',
															required : true,
														},
														controller : function(
																$scope) {
															$scope
																	.$watch(
																			'model.totalAmount',
																			function(
																					newValue,
																					oldValue,
																					theScope) {
																				if (newValue !== oldValue) {
																					var totalAmount = newValue ? newValue
																							: 0;
																					taxAmount = Math
																							.round(taxPercent
																									* totalAmount
																									/ 100);
																					$scope.model[$scope.options.key] = taxAmount;
																				}
																			});

														}
													}

											]
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
											type : 'auditParameterSection',
											key : 'parameterValues',
											templateOptions : {
												label : $translate.instant('Parameter'),
												'fields' : []
											}
										};

										var totalAmount = {
											key : 'totalAmount',
											type : 'input',
											templateOptions : {
												label : $translate.instant('Total amount'),
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
															var taxPercent = vm.formData.model.expenseType.taxPercent;
															var totalAmount = vm.formData.model.totalAmount ? vm.formData.model.totalAmount
																	: 0;
															taxAmount = Math
																	.round(taxPercent
																			* totalAmount
																			/ 100);
															vm.formData.model.taxAmount = taxAmount;
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
												'label' : $translate.instant('Type'),
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
															$scope, newValue);
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
				'edit-memberaudit',
				function($rootScope, $scope, $stateParams, $state, $translate,
						formlyVersion, $uibModal, memberAuditService,
						memberExpenseTypeService,
						memberAuditAttendRecordService) {
					var id = parseInt($stateParams.reportid);
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

					memberAuditService
							.get(userId, id)
							.then(
									function(status) {
										angular.forEach(status.data.expenses, function(val) {
											val.expenseType.value = $translate.instant(val.expenseType.value);
										});
										vm.model = status.data;
									
										var recordId = status.data.attendanceRecordId;
										if (recordId) {
											memberAuditAttendRecordService
													.get(userId, recordId)
													.success(
															function(status) {

																vm.model.attendanceRecordId = status.id;
																if (id == 0) {
																	vm.model.reason = status.reason;
																}
																vm.recordModel.reason = status.reason;
																vm.recordModel.duration = status.duration;
																vm.recordModel.startDate = status.startDate;
																vm.recordModel.endDate = status.endDate;
																vm.recordModel.applicant = status.applicant;
															})
													.error(
															function() {
																console
																		.debug('retrieve record failed');
															});
										}
									});

					vm.expenseTypes = [];
					memberExpenseTypeService
							.list()
							.then(
									function(response) {
										angular.forEach(response.data.content, function(val) {
											val.value = $translate.instant(val.value);
											val.expenseCategory.name = $translate.instant(val.expenseCategory.name);
											vm.expenseTypes.push(val);
										});
										var totalPages = response.data.totalPages;
										if (totalPages > 1) {
											for (var i = 1; i < totalPages; i++) {
												memberExpenseTypeService.list({page:i}).then(function(response2) {
													angular.forEach(response2.data.content, function(val) {
														val.value = $translate.instant(val.value);
														val.expenseCategory.name = $translate.instant(val.expenseCategory.name);
														vm.expenseTypes.push(val);
													});
												});
											}
										}
									});
					vm.attendRecords = [];

					vm.fields = [ {
						className : 'section-label',
						template : '<div><strong>'+$translate.instant('Attend Record')+':</strong></div>'
					}, {
						className : 'row',
						fieldGroup : [ {
							className : 'col-xs-6',
							type : 'input',
							key : 'applicant.name',
							model : vm.recordModel,
							templateOptions : {
								label : $translate.instant('Applicant'),
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
								label : $translate.instant('Reason'),
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
								label : $translate.instant('StartDate'),
								disabled : true
							}
						}, {
							className : 'col-xs-2',
							type : 'input',
							key : 'endDate',
							model : vm.recordModel,
							templateOptions : {
								label : $translate.instant('EndDate'),
								disabled : true
							}
						}, {
							className : 'col-xs-2',
							type : 'input',
							key : 'duration',
							model : vm.recordModel,
							templateOptions : {
								label : $translate.instant('Duration'),
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
								label : $translate.instant('Firm/Project'),
								placeholder : $translate.instant('Firm/Project'),
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
								label : $translate.instant('Route'),
								placeholder : $translate.instant('Source - Destination')
							}
						} ]
					}, {
						className : 'row',
						fieldGroup : [ {
							className : 'col-xs-4',
							key : 'comment',
							type : 'textarea',
							templateOptions : {
								label : $translate.instant('comment'),
								placeholder : $translate.instant('comment')
							}
						} ]
					}, {
						className : 'row',
						fieldGroup : [ {
							className : 'col-xs-12',
							type : 'auditExpenseSection',
							key : 'expenses',
							wrapper : 'panel',
							templateOptions : {
								label : $translate.instant('Expense'),
								btnText : $translate.instant('Add'),
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
								memberAuditService
										.update(userId, id, vm.model)
										.then(
												function(status) {
													$state
															.go(
																	'dashboard.list-memberaudits',
																	{
																		userid : userId
																	});
												});
							} else {
								$state.go('dashboard.list-memberaudits', {
									userid : userId
								});
							}

						}
					}

				});
