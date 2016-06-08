angular
.module(
		'hello',
		[ 'oc.lazyLoad', 'ui.router', 'ui.bootstrap',
		  'angular-loading-bar', 'formly', 'formlyBootstrap',
		  'ui.select', 'ngSanitize', 'smart-table', 'auth',
		  'navigation' ])
		  .config(
				  [
				   '$stateProvider',
				   '$urlRouterProvider',
				   '$ocLazyLoadProvider',
				   '$httpProvider',
				   'formlyConfigProvider',
				   function($stateProvider, $urlRouterProvider,
						   $ocLazyLoadProvider, $httpProvider,
						   formlyConfigProvider) {

					   $ocLazyLoadProvider.config({
						   debug : false,
						   events : true,
					   });

					   $urlRouterProvider.otherwise('/dashboard/home');

					   $stateProvider.state('login', {
						   url : '/login',
						   controller : 'navigation',
						   templateUrl : 'login.html'
					   }).state('dashboard', {
						   url : '/dashboard',
						   controller : 'navigation',
						   templateUrl : 'main.html'
					   }).state('dashboard.home', {
						   url : '/home',
						   templateUrl : 'home.html'
					   }).state('dashboard.edit-profile',{
                		   templateUrl:'edit.html',
                		   controller: 'edit-profile',
                		   url:'/edit-profile',
                		   resolve : {
                			   loadMyDirectives:function($ocLazyLoad){
                				   return $ocLazyLoad.load(
                						   {
                							   name:'edit-profile',
                							   files:[
                							          'profile/edit-profile.js'
                							          ]
                						   })
                			   },
                			   employee : function(
                					   auth,
                					   profileService) {

                				   var id = auth.user.principal.id;
                				   if(id == 0){
                					   return {data:{}};
                				   }
                				   return profileService.get(id);
                			   }
                		   }
                	   }).state('dashboard.edit-memberreport', {
						   templateUrl:'edit.html',
						   controller: 'edit-memberreport',
						   url:'/edit-memberreport/:id',
						   resolve : {
							   loadMyDirectives:function($ocLazyLoad){
								   return $ocLazyLoad.load(
										   {
											   name:'edit-memberreport',
											   files:[
											          'report/edit-memberreport.js'
											          ]
										   })
							   },
							   report : function(
									   memberReportService,
									   $stateParams) {
								   var id = $stateParams.id || 0;
								   if(id == 0){
									   return {data:{}};
								   }
								   return memberReportService.get(id);
							   }
						   }
					   }).state('dashboard.list-memberreports', {
						   url : '/list-memberreports',
						   controller : 'list-memberreports',
						   templateUrl : 'report/list-memberreports.html',
						   params : {
							   startDate : null,
							   endDate : null,
							   currentStatus : null
						   },
						   resolve : {
							   loadMyDirectives : function($ocLazyLoad) {
								   return $ocLazyLoad.load({
									   name : 'list-memberreports',
									   files : [ 'report/list-memberreports.js' ]
								   })
							   }
						   }
					   }).state('dashboard.create-memberreports', {
						   url : '/create-memberreports',
						   controller : 'create-memberreports',
						   templateUrl : 'createReport/create-memberreports.html',
						   resolve : {
							   loadMyDirectives:function($ocLazyLoad){
								   return $ocLazyLoad.load(
										   {
											   name:'add-memberreport',
											   files:[
											          'createReport/add-memberreport.js'
											          ]
										   })
							   },
							   report : function(
									   memberReportService,
									   $stateParams) {
								   var id = $stateParams.id || 0;
								   if(id == 0){
									   return {data:{}};
								   }
								   return memberReportService.get(id);
							   }
						   }
						   
					   }).state('dashboard.expense', {
						   url : '/expense',
						   controller : 'expenseController',
						   templateUrl : 'expense/expense.html'
					   }).state('dashboard.photo', {
						   url : '/photo',
						   controller : 'photoController',
						   templateUrl : 'expense/photo.html'
					   }).state('dashboard.category', {
						   url : '/category',
						   controller : 'categoryController',
						   templateUrl : 'expenseCategory/eCategory.html'
					   }).state('dashboard.type', {
						   url : '/type',
						   controller : 'typeController',
						   templateUrl : 'expenseType/eType.html'
					   });

					   $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
					   /*
					    * Register error provider that shows message on
					    * failed requests or redirects to login page on
					    * unauthenticated requests
					    */
					   $httpProvider.interceptors
					   .push('authHttpResponseInterceptor');
				   } ])
				   .factory(
						   'authHttpResponseInterceptor',
						   [
						    '$q',
						    '$rootScope',
						    '$location',
						    function($q, $rootScope, $location) {
						    	return {
						    		response : function(response) {
						    			if (response.status === 401) {
						    				console.log("Response 401");
						    			}
						    			return response || $q.when(response);
						    		},
						    		responseError : function(rejection) {
						    			var data = rejection.data;
						    			var status = rejection.status;
						    			var config = rejection.config;
						    			var method = config.method;
						    			var url = config.url;
						    			if (rejection.status === 401) {
						    				console.log("Response Error 401",
						    						rejection);
						    				$location.path('/login').search(
						    						'returnTo', $location.path());
						    			} else {
						    				$rootScope.error = data.message;
						    			}
						    			return $q.reject(rejection);
						    		}
						    	}
						    } ])
						    .run(
						    		function($rootScope, $http, $parse, auth, formlyConfig,
						    				formlyValidationMessages) {

						    			formlyConfig.extras.removeChromeAutoComplete = true;

						    			formlyConfig.extras.errorExistsAndShouldBeVisibleExpression = 'fc.$touched || form.$submitted';

						    			formlyValidationMessages.addStringMessage('required',
						    			'This field is required');

						    			formlyConfig.extras.fieldTransform = formlyConfig.extras.fieldTransform
						    			|| [];
						    			formlyConfig.extras.fieldTransform
						    			.push(removeOnHideTransformer);

						    			function removeOnHideTransformer(fields) {
						    				return fields.map(function(field) {
						    					field.data = field.data || {};
						    					if (field.key && !field.noFormControl
						    							&& field.hideExpression
						    							&& !field.data.dontRemoveOnHidden) {
						    						addFieldRemoveOnHideWatcher(field);
						    					}
						    					return field;
						    				});
						    			}
						    			;

						    			function addFieldRemoveOnHideWatcher(field) {
						    				var watcher = getWatcher();
						    				if (field.watcher) {
						    					if (!angular.isArray(field.watcher)) {
						    						field.watcher = [ field.watcher ];
						    					}
						    					field.watcher.push(watcher);
						    				} else {
						    					field.watcher = watcher;
						    				}
						    			};

						    			function getWatcher() {
						    				return {
						    					expression : function(field) {
						    						return field.hide;
						    					},
						    					listener : function(field, newHide, oldHide, scope) {
						    						var model = field.model || scope.model; // default
						    						// to
						    						// the
						    						// field's
						    						// model
						    						/* istanbul ignore else */
						    						if (field.hide) {
						    							var getter = $parse(field.key);
						    							field.data.preHiddenValue = getter(model);
						    							getter.assign(model, undefined);
						    						} else if (field.data.preHiddenValue !== undefined) {
						    							$parse(field.key).assign(model,
						    									field.data.preHiddenValue);
						    						} else {
						    							// do nothing.
						    						}
						    					}
						    				};
						    			}
						    			;
						    			auth.init('/home', '/login', 'logout');

						    			/* Reset error when a new view is loaded */
						    			$rootScope.$on('$viewContentLoaded', function() {
						    				delete $rootScope.error;
						    				delete $rootScope.info;
						    			});

						    			$rootScope.back = function() {
						    				window.history.back();
						    			};

						    			$rootScope.hasRole = function(role) {

						    				if ($rootScope.user === undefined) {
						    					return false;
						    				}

						    				if ($rootScope.user.roles[role] === undefined) {
						    					return false;
						    				}

						    				return $rootScope.user.roles[role];
						    			};

						    			$rootScope.initialized = true;

						    			var attributes = [ 'date-disabled', 'custom-class',
						    			                   'show-weeks', 'starting-day', 'init-date',
						    			                   'min-mode', 'max-mode', 'format-day',
						    			                   'format-month', 'format-year', 'format-day-header',
						    			                   'format-day-title', 'format-month-title',
						    			                   'year-range', 'shortcut-propagation',
						    			                   'datepicker-popup', 'show-button-bar',
						    			                   'current-text', 'clear-text', 'close-text',
						    			                   'close-on-date-selection',
						    			                   'datepicker-append-to-body' ,'meridians',
						    			                   'readonly-input',
						    			                   'mousewheel',
						    			                   'arrowkeys'];

						    			var bindings = [ 'datepicker-mode', 'min-date', 'max-date', 'hour-step',
						    			                 'minute-step','show-meridian' ];

						    			var ngModelAttrs = {};

						    			angular.forEach(attributes, function(attr) {
						    				ngModelAttrs[camelize(attr)] = {
						    						attribute : attr
						    				};
						    			});

						    			angular.forEach(bindings, function(binding) {
						    				ngModelAttrs[camelize(binding)] = {
						    						bound : binding
						    				};
						    			});

						    			formlyConfig.setType({
						    				name: 'ui-select-single-search',
						    				extends: 'select',
						    				templateUrl: 'templates/ui-select-single-async-search.html'
						    			});

						    			formlyConfig.setType({
						    				name : 'datepicker',
						    				templateUrl : 'templates/datepicker.html',
						    				wrapper : [ 'bootstrapLabel', 'bootstrapHasError' ],
						    				defaultOptions : {
						    					ngModelAttrs : ngModelAttrs,
						    					templateOptions : {
						    						datepickerOptions : {
						    							format : 'yyyy-MM-dd',
						    							initDate : new Date()
						    						}
						    					}
						    				},
						    				controller: [
						    				             '$scope', function ($scope) {
						    				            	 $scope.datepicker = {};

						    				            	 // make sure the initial
						    				            	 // value is of
						    				            	 // type DATE!
						    				            	 var currentModelVal = $scope.model[$scope.options.key];
						    				            	 if (typeof (currentModelVal) == 'string'){
						    				            		 $scope.model[$scope.options.key] = new Date(currentModelVal);
						    				            	 }


						    				            	 $scope.datepicker.opened = false;

						    				            	 $scope.datepicker.open = function ($event) {
						    				            		 $scope.datepicker.opened = true;
						    				            	 };
						    				             }
						    				             ]
						    			});

						    			formlyConfig.setType({
						    				name: 'timepicker',
						    				template: '<timepicker ng-model="model[options.key]"></timepicker>',
						    				wrapper: ['bootstrapLabel', 'bootstrapHasError'],
						    				defaultOptions: {
						    					ngModelAttrs: ngModelAttrs,
						    					templateOptions: {
						    						datepickerOptions: {}
						    					}
						    				},
						    				controller: [
						    				             '$scope', function ($scope) {
						    				            	 $scope.datepicker = {};

						    				            	 // make sure the initial
						    				            	 // value is of
						    				            	 // type DATE!
						    				            	 var currentModelVal = $scope.model[$scope.options.key];
						    				            	 if (typeof (currentModelVal) == 'string'){
						    				            		 $scope.model[$scope.options.key] = new Date(currentModelVal);
						    				            	 }

						    				            	 $scope.datepicker.opened = false;

						    				            	 $scope.datepicker.open = function ($event) {
						    				            		 $scope.datepicker.opened = true;
						    				            	 };
						    				             }
						    				             ]
						    			});

						    			function camelize(string) {
						    				string = string.replace(/[\-_\s]+(.)?/g, function(
						    						match, chr) {
						    					return chr ? chr.toUpperCase() : '';
						    				});
						    				// Ensure 1st char is always lowercase
						    				return string.replace(/^([A-Z])/, function(match, chr) {
						    					return chr ? chr.toLowerCase() : '';
						    				});
						    			}
						    		}).factory(
				    						  'profileService',
				    						  [
				    						   'auth',
				    						   '$http',
				    						   function(auth, $http) {
                  				    			 var serviceBase = 'rest/v1.0/users/';
                				    			 var obj = {};
                				    			 obj.list = function(queries) {
                				    				 return $http.get(serviceBase, {params:queries});
                				    			 };

                				    			 obj.get = function(id) {
                				    				 return $http.get(serviceBase  + id);
                				    			 };

                				    			 obj.insert = function(employee) {
                				    				 return $http.post(serviceBase, employee);
                				    			 };

                				    			 obj.update = function(id, employee) {
                				    				 return $http.put(serviceBase  + id,
                				    						 employee).then(function(results) {
                				    							 return results;
                				    						 });
                				    			 };

                				    			 obj.remove = function(id) {
                				    				 return $http.delete(serviceBase + id).then(function(status) {
                				    					 return status;
                				    				 });
                				    			 };

                				    			 return obj;
                				    		 } ])
					.factory(
					'memberDepartmentService',
					[
							'$http',
							function($http) {
								var serviceBase = 'rest/v1.0/departments/';
								var obj = {};
								obj.list = function(queries) {
									return $http.get(serviceBase, {params:queries});
								};

								obj.get = function(id) {
									return $http.get(serviceBase  + id);
								};

								obj.insert = function(department) {
									return $http.post(serviceBase, department).then(
											function(results) {
												return results;
											});
								};

								obj.update = function(id, department) {
									return $http.put(serviceBase  + id,
											department).then(function(results) {
										return results;
									});
								};
								
								obj.remove = function(id) {
									return $http.delete(serviceBase + id).then(function(status) {
										return status;
									});
								};

								return obj;
							} ]);