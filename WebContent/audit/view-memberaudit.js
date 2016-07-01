angular
		.module('view-memberaudit',
				[ 'formly', 'formlyBootstrap', 'ngAnimate', 'ngMessages' ])
		.constant('formlyExampleApiCheck', apiCheck())
		.run(
				function(formlyConfig, formlyValidationMessages) {
					formlyConfig.extras.errorExistsAndShouldBeVisibleExpression = 'fc.$touched || form.$submitted';

					formlyValidationMessages.addStringMessage('required',
							'This field is required');
				})
		.filter(
				'taiwanDate',
				[
						'$filter',
						function($filter) {
							return function(input, format) {
								if (!input || !format) {
									return '';
								}
								var yearPart = $filter("date")(input, "yyyy");
								var monthPart = $filter("date")(input, "MM");
								var dayPart = $filter("date")(input, "dd");
								var taiwanYear = (parseInt(yearPart) - 1911);
								return format.replace('yyy', taiwanYear)
										.replace('MM', monthPart).replace('dd',
												dayPart);
							}
						} ])
		.controller(
				'view-memberaudit',
				function($rootScope, $scope, $stateParams, $state,
						formlyVersion, memberAuditService,
						memberExpenseTypeService, profileService,
						memberAuditStatusChangeService) {
					var userId = $stateParams.userid;
					var id = $stateParams.reportid;

					$scope.rowCollection = [];
					memberAuditStatusChangeService.list(userId, id).then(
							function(response) {
								angular.forEach(response.data.content,
										function(val) {
											$scope.rowCollection.push(val);
										});
							});

					var vm = this;
					$scope.vm = vm;
					vm.onApprove = onApprove;
					vm.onReject = onReject;
					vm.author = {
						name : 'pohsun',
						url : ''
					};
					vm.env = {
						angularVersion : angular.version.full,
						formlyVersion : formlyVersion
					};
					vm.model = {};
					vm.options = {
						formState : {
							awesomeIsForced : false
						}
					};

					vm.fields = [ {
						key : 'comment',
						type : 'textarea',
						templateOptions : {
							label : 'Comment',
							placeholder : 'comment',
						}
					} ];
					function onApprove() {
						if (vm.form.$valid) {
							if (id > 0) {
								memberAuditService
										.approve(userId, id, vm.model)
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

					function onReject() {
						if (vm.form.$valid) {
							if (id > 0) {
								memberAuditService
										.reject(userId, id, vm.model)
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

					$scope.trafficSpan = 5;
					$scope.commentSpan = 4;
					var extraTraffics = []
					$scope.extraTraffics = extraTraffics;
					var traffics = [];
					$scope.traffics = traffics;
					for (var i = 0; i < $scope.trafficSpan; i++) {
						$scope.traffics[i] = {};
					}
					$scope.totalAmount = 0;
					$scope.taxAmount = 0;
					$scope.applicant = {};
					$scope.other = 0;

					profileService.get(userId).then(function(status) {
						$scope.applicant.jobTitle = status.data.jobTitle;
					});

					var transportationCategoryId = 67
					memberAuditService
							.get(userId, id)
							.then(
									function(status) {
										$scope.model = status.data;
										var expenses = status.data.expenses;
										var tid = 0;
										angular
												.forEach(
														expenses,
														function(expense) {
															if (expense.taxAmount) {
																$scope.taxAmount = $scope.taxAmount
																		+ expense.taxAmount;
															}
															if (expense.totalAmount) {
																$scope.totalAmount = $scope.totalAmount
																		+ expense.totalAmount
																		+ expense.taxAmount;
															}

															//transportation expense
															if (expense.expenseType.expenseCategory.id == transportationCategoryId) {
																var find = false;
																// find in
																// primary 6
																// rows
																for (var index = 0; index < traffics.length; index++) {
																	if (expense.expenseType.id == traffics[index]['id']) {
																		find = true;
																		traffics[index]['totalAmount'] = traffics[index]['totalAmount']
																				+ expense.totalAmount;
																		break;
																	}
																}
																// find in extra
																// rows
																if (!find) {
																	for (var index = 0; index < extraTraffics.length; index++) {
																		if (expense.expenseType.id == extraTraffics[index]['id']) {
																			find = true;
																			extraTraffics[index]['totalAmount'] = extraTraffics[index]['totalAmount']
																					+ expense.totalAmount;
																			break;
																		}
																	}
																}
																// add new row
																if (!find) {
																	if (tid < 6) {
																		traffics[tid]['id'] = expense.expenseType.id;
																		traffics[tid]['name'] = expense.expenseType.value;
																		if(expense.totalAmount){
																		   traffics[tid]['totalAmount'] = expense.totalAmount;
																		}else{
																			traffics[tid]['totalAmount'] =0;
																		}

																	} else {
																		var etid = tid - 6;
																		extraTraffics[etid]['id'] = expense.expenseType.id;
																		extraTraffics[etid]['name'] = expense.expenseType.value;
																		if (expense.totalAmount) {
																		  extraTraffics[etid]['totalAmount'] = expense.totalAmount;
																		}else{
																			extraTraffics[etid]['totalAmount'] = 0;
																		}
																	}
																	tid++;
																}
																// other expense
															} else {
																if (expense.totalAmount) {
																	$scope.other = $scope.other
																			+ expense.totalAmount;
																}
															}

														});
									});

//					memberExpenseTypeService
//							.list()
//							.then(
//									function(response) {
//										var trafficSpan = 0;
//										angular
//												.forEach(
//														response.data.content,
//														function(val) {
//															if (trafficSpan <= 6) {
//																$scope.traffics[trafficSpan]['name'] = val.value;
//																$scope.traffics[trafficSpan]['id'] = val.id;
//																$scope.traffics[trafficSpan]['totalAmount'] = 0;
//															}
//															if (val.expenseCategory.id == 1) {
//																trafficSpan++;
//															}
//															if (trafficSpan > 6) {
//																$scope.extraTraffics
//																		.push({
//																			id : val.id,
//																			name : val.value,
//																			totalAmount : 0
//																		});
//															}
//														});
//										if (trafficSpan >= 6) {
//											$scope.trafficSpan = trafficSpan;
//											$scope.commentSpan = 4 + trafficSpan - 6;
//										}
//
//										memberAuditService
//												.get(userId, id)
//												.then(
//														function(status) {
//															$scope.model = status.data;
//															var expenses = status.data.expenses;
//															angular
//																	.forEach(
//																			expenses,
//																			function(
//																					expense) {
//																				if (expense.taxAmount) {
//																					$scope.taxAmount = $scope.taxAmount
//																							+ expense.taxAmount;
//																				}
//																				if (expense.totalAmount) {
//																					$scope.totalAmount = $scope.totalAmount
//																							+ expense.totalAmount
//																							+ expense.taxAmount;
//																				}
//																				expense.expenseType.id;
//																				var find = false;
//																				var index = 0;
//																				for (var index = 0; index < traffics.length; index++) {
//																					if (expense.expenseType.id == traffics[index]['id']) {
//																						find = true;
//																						traffics[index]['totalAmount'] = traffics[index]['totalAmount']
//																								+ expense.totalAmount;
//																						break;
//																					}
//																				}
//																				if (!find) {
//																					for (var index = 0; index < extraTraffics.length; index++) {
//																						if (expense.expenseType.id == extraTraffics[index]['id']) {
//																							find = true;
//																							extraTraffics[index]['totalAmount'] = extraTraffics[index]['totalAmount']
//																									+ expense.totalAmount;
//																							break;
//																						}
//																					}
//																				}
//																			});
//														});
//									});
				});
