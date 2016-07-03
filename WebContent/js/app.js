angular
.module(
		'hello',
		[ 'oc.lazyLoad', 'ui.router', 'ui.bootstrap', 'ngCookies', 'pascalprecht.translate',
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
				   '$translateProvider',
				   function($stateProvider, $urlRouterProvider,
						   $ocLazyLoadProvider, $httpProvider,
						   formlyConfigProvider,$translateProvider) {

					   formlyConfigProvider.setWrapper({
						   name : 'panel',
						   templateUrl : 'templates/panel.html'
					   });

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
						   url : '/:userid/list-memberreports',
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
					   }).state('dashboard.edit-profile',{
						   templateUrl:'profile/edit-profile.html',
						   controller: 'edit-profile',
						   url:'/:userid/edit-profile',
						   resolve : {
							   loadMyDirectives:function($ocLazyLoad){
								   return $ocLazyLoad.load(
										   {
											   name:'edit-profile',
											   files:[
											          'profile/edit-profile.js'
											          ]
										   })
							   }
						   }
					   }).state('dashboard.edit-memberreport', {
						   templateUrl:'edit.html',
						   controller: 'edit-memberreport',
						   url:'/:userid/list-memberrecords/:recordid/report/:reportid',
						   resolve : {
							   loadMyDirectives:function($ocLazyLoad){
								   return $ocLazyLoad.load(
										   {
											   name:'edit-memberreport',
											   files:[
											          'report/edit-memberreport.js'
											          ]
										   })
							   }
						   }
					   }).state('dashboard.list-memberreports', {
						   url : '/:userid/list-memberreports',
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
					   }).state('dashboard.list-memberrecords', {
						   url : '/:userid/list-memberrecords',
						   controller : 'list-memberrecords',
						   templateUrl : 'record/list-memberrecords.html',
						   params : {
							   startDate : null,
							   endDate : null,
							   currentStatus : null
						   },
						   resolve : {
							   loadMyDirectives : function($ocLazyLoad) {
								   return $ocLazyLoad.load({
									   name : 'list-memberrecords',
									   files : [ 'record/list-memberrecords.js' ]
								   })
							   }
						   }
					   }).state('dashboard.list-memberaudits', {
						   url : '/:userid/list-memberaudits',
						   controller : 'list-memberaudits',
						   templateUrl : 'audit/list-memberaudits.html',
						   params : {
							   startDate : null,
							   endDate : null,
							   currentStatus : null
						   },
						   resolve : {
							   loadMyDirectives : function($ocLazyLoad) {
								   return $ocLazyLoad.load({
									   name : 'list-memberaudits',
									   files : [ 'audit/list-memberaudits.js' ]
								   })
							   }
						   }
					   }).state('dashboard.edit-memberaudit', {
						   url : '/:userid/memberaudit/:reportid',
						   controller : 'edit-memberaudit',
						   templateUrl : 'edit.html',
						   resolve : {
							   loadMyDirectives : function($ocLazyLoad) {
								   return $ocLazyLoad.load({
									   name : 'edit-memberaudit',
									   files : [ 'audit/edit-memberaudit.js' ]
								   })
							   }
						   }
					   }).state('dashboard.view-memberaudit', {
						   url : '/:userid/memberaudit/:reportid/view',
						   templateUrl : 'audit/view-memberaudit.html',
						   controller : 'view-memberaudit',
						   resolve : {
							   loadMyDirectives : function($ocLazyLoad) {
								   return $ocLazyLoad.load({
									   name : 'view-memberaudit',
									   files : [ 'audit/view-memberaudit.js' ]
								   })
							   }
						   }
					   }).state('dashboard.edit-jobTitle', {
						   templateUrl:'edit.html',
						   controller: 'edit-jobTitle',
						   url:'/admin/jobTitles/:id',
						   resolve : {
							   loadMyDirectives:function($ocLazyLoad){
								   return $ocLazyLoad.load(
										   {
											   name:'edit-jobTitle',
											   files:[
											          'jobTitle/edit-jobTitle.js'
											          ]
										   })
							   }
						   }
					   }).state('dashboard.list-jobTitles', {
						   url : '/admin/jobTitles',
						   controller : 'list-jobTitles',
						   templateUrl : 'jobTitle/list-jobTitles.html',
						   params : {
							   name : null
						   },
						   resolve : {
							   loadMyDirectives : function($ocLazyLoad) {
								   return $ocLazyLoad.load({
									   name : 'list-jobTitles',
									   files : [ 'jobTitle/list-jobTitles.js' ]
								   })
							   }
						   }
					   }).state('dashboard.edit-user', {
						   templateUrl:'user/edit-user.html',
						   controller: 'edit-user',
						   url:'/admin/users/:id',
						   resolve : {
							   loadMyDirectives:function($ocLazyLoad){
								   return $ocLazyLoad.load(
										   {
											   name:'edit-user',
											   files:[
											          'user/edit-user.js'
											          ]
										   })
							   }
						   }
					   }).state('dashboard.list-users', {
						   url : '/admin/users',
						   controller : 'list-users',
						   templateUrl : 'user/list-users.html',
						   params : {
							   name : null
						   },
						   resolve : {
							   loadMyDirectives : function($ocLazyLoad) {
								   return $ocLazyLoad.load({
									   name : 'list-users',
									   files : [ 'user/list-users.js' ]
								   })
							   }
						   }
					   }).state('dashboard.edit-expenseType', {
						   templateUrl:'edit.html',
						   controller: 'edit-expenseType',
						   url:'/admin/expenseTypes/:id',
						   resolve : {
							   loadMyDirectives:function($ocLazyLoad){
								   return $ocLazyLoad.load(
										   {
											   name:'edit-expenseType',
											   files:[
											          'expenseType/edit-expenseType.js'
											          ]
										   })
							   }
						   }
					   }).state('dashboard.list-expenseTypes', {
						   url : '/admin/expenseTypes',
						   controller : 'list-expenseTypes',
						   templateUrl : 'expenseType/list-expenseTypes.html',
						   params : {
							   value : null
						   },
						   resolve : {
							   loadMyDirectives : function($ocLazyLoad) {
								   return $ocLazyLoad.load({
									   name : 'list-expenseTypes',
									   files : [ 'expenseType/list-expenseTypes.js' ]
								   })
							   }
						   }
					   });

					   $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
					   /*
						 * Register error provider that shows message on failed
						 * requests or redirects to login page on
						 * unauthenticated requests
						 */
					   $httpProvider.interceptors
					   .push('authHttpResponseInterceptor');
					   
					   $translateProvider.useCookieStorage();
					   $translateProvider.useStaticFilesLoader({
						    prefix: '/Btrs/lang/',
						    suffix: '.json'
						});
					   $translateProvider.preferredLanguage('en');
					   
				   } ]).directive('localeSelector', function($translate) {
				        return {
				            restrict: 'C',
				            replace: true,
				            templateUrl: 'templates/locale-selector.html',
				            link: function(scope, elem, attrs) {
				                // Get active locale even if not loaded yet:
				                scope.locale = $translate.proposedLanguage();
				 
				                scope.setLocale = function() {
				                    $translate.use(scope.locale);
				                };
				            }
				        };
				    })
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
						    			};
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

						    			formlyConfig.setWrapper({
						    				name: 'loading',
						    				templateUrl: 'templates/loading.html'
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

						    				            	 // make sure the
						    				            	 // initial
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

						    				            	 // make sure the
						    				            	 // initial
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
						    				// Ensure 1st char is always
						    				// lowercase
						    				return string.replace(/^([A-Z])/, function(match, chr) {
						    					return chr ? chr.toLowerCase() : '';
						    				});
						    			}
						    		}).factory(
						    				'profileService',
						    				[
						    				 '$http',
						    				 function($http) {
						    					 var serviceBase = 'rest/v1.0/users/';
						    					 var obj = {};
						    					 obj.list = function(queries) {
						    						 return $http.get(serviceBase, {params:queries});
						    					 };

						    					 obj.get = function(id) {
						    						 return $http.get(serviceBase  + id);
						    					 };

						    					 obj.update = function(id, employee) {
						    						 return $http.put(serviceBase  + id,
						    								 employee).then(function(results) {
						    									 return results;
						    								 });
						    					 };


						    					 return obj;
						    				 } ]).factory(
						    						 'memberReportService',
						    						 [
						    						  '$http',
						    						  function($http) {
						    							  var serviceBase = 'rest/v1.0/users/';
						    							  var obj = {};
						    							  obj.list = function(userid, queries) {
						    								  var url  = serviceBase + userid + '/reports/';
						    								  return $http.get(url, {params:queries});
						    							  };

						    							  obj.get = function(userid, id) {
						    								  var url  = serviceBase + userid + '/reports/';
						    								  return $http.get(url  + id);
						    							  };

						    							  obj.insert = function(userid, record) {
						    								  var url  = serviceBase + userid + '/reports/';
						    								  return $http.post(url, record);
						    							  };

						    							  obj.update = function(userid, id, record) {
						    								  var url  = serviceBase + userid + '/reports/';
						    								  return $http.put(url  + id,
						    										  record);
						    							  };

						    							  obj.remove = function(userid, id) {
						    								  var url  = serviceBase + userid + '/reports/';
						    								  return $http.delete(url + id);
						    							  };

						    							  return obj;
						    						  } ]).factory(
						    								  'memberAuditService',
						    								  [
						    								   '$http',
						    								   function($http) {
						    									   var serviceBase = 'rest/v1.0/users/';
						    									   var obj = {};
						    									   obj.list = function(userid, queries) {
						    										   var url  = serviceBase + userid + '/audits/';
						    										   return $http.get(url, {params:queries});
						    									   };

						    									   obj.get = function(userid, id) {
						    										   var url  = serviceBase + userid + '/audits/';
						    										   return $http.get(url  + id);
						    									   };

						    									   obj.insert = function(userid, record) {
						    										   var url  = serviceBase + userid + '/audits/';
						    										   return $http.post(url, record);
						    									   };

						    									   obj.update = function(userid, id, record) {
						    										   var url  = serviceBase + userid + '/audits/';
						    										   return $http.put(url  + id,
						    												   record);
						    									   };

						    									   obj.remove = function(userid, id) {
						    										   var url  = serviceBase + userid + '/audits/';
						    										   return $http.delete(url + id);
						    									   };

						    									   obj.approve = function(userid, id, statusChange) {
						    										   var url  = serviceBase + userid + '/audits/';
						    										   return $http.post(url + id + "/approve", statusChange);
						    									   };

						    									   obj.reject = function(userid, id, statusChange) {
						    										   var url  = serviceBase + userid + '/audits/';
						    										   return $http.post(url + id + "/reject", statusChange);
						    									   };

						    									   return obj;
						    								   } ])
						    								   .factory(
						    										   'memberExpenseTypeService',
						    										   [
						    										    '$http',
						    										    function($http) {
						    										    	var serviceBase = 'rest/v1.0/expensetypes/';
						    										    	var obj = {};
						    										    	obj.list = function(queries) {
						    										    		return $http.get(serviceBase, {params:queries});
						    										    	};

						    										    	obj.get = function(id) {
						    										    		return $http.get(serviceBase  + id);
						    										    	};

						    										    	return obj;
						    										    } ]).factory(
						    										    		'memberRecordService',
						    										    		[
						    										    		 '$http',
						    										    		 function($http) {
						    										    			 var serviceBase = 'rest/v1.0/users/';
						    										    			 var obj = {};
						    										    			 obj.list = function(userId, queries) {
						    										    				 var url  = serviceBase + userId + '/records/available';
						    										    				 return $http.get(url, {params:queries});
						    										    			 };

						    										    			 obj.get = function(userId, id) {
						    										    				 var url  = serviceBase + userId + '/records/';
						    										    				 return $http.get(url  + id);
						    										    			 };

						    										    			 return obj;
						    										    		 } ]).factory(
						    										    				 'memberAuditStatusChangeService',
						    										    				 [
						    										    				  '$http',
						    										    				  function($http) {
						    										    					  var serviceBase = 'rest/v1.0/users/';
						    										    					  var obj = {};
						    										    					  obj.list = function(userid, reportid, queries) {
						    										    						  var url  = serviceBase + userid + '/audits/'+reportid+'/statusChanges';
						    										    						  return $http.get(url, {params:queries});
						    										    					  };

						    										    					  return obj;
						    										    				  } ]).factory(
						    										    						  'jobTitleService',
						    										    						  [
						    										    						   '$http',
						    										    						   function($http) {
						    										    							   var serviceBase = 'rest/v1.0/admin';
						    										    							   var obj = {};
						    										    							   obj.list = function( queries) {
						    										    								   var url  = serviceBase + '/jobTitles/';
						    										    								   return $http.get(url, {params:queries});
						    										    							   };

						    										    							   obj.get = function( id) {
						    										    								   var url  = serviceBase + '/jobTitles/';
						    										    								   return $http.get(url  + id);
						    										    							   };

						    										    							   obj.insert = function(record) {
						    										    								   var url  = serviceBase + '/jobTitles/';
						    										    								   return $http.post(url, record);
						    										    							   };

						    										    							   obj.update = function( id, record) {
						    										    								   var url  = serviceBase + '/jobTitles/';
						    										    								   return $http.put(url  + id,
						    										    										   record);
						    										    							   };

						    										    							   obj.remove = function(id) {
						    										    								   var url  = serviceBase + '/jobTitles/';
						    										    								   return $http.delete(url + id);
						    										    							   };

						    										    							   return obj;
						    										    						   } ]).factory(
						    										    								   'userService',
						    										    								   [
						    										    								    '$http',
						    										    								    function($http) {
						    										    								    	var serviceBase = 'rest/v1.0/admin';
						    										    								    	var obj = {};
						    										    								    	obj.list = function( queries) {
						    										    								    		var url  = serviceBase + '/users/';
						    										    								    		return $http.get(url, {params:queries});
						    										    								    	};

						    										    								    	obj.get = function( id) {
						    										    								    		var url  = serviceBase + '/users/';
						    										    								    		return $http.get(url  + id);
						    										    								    	};

						    										    								    	obj.insert = function(record) {
						    										    								    		var url  = serviceBase + '/users/';
						    										    								    		return $http.post(url, record);
						    										    								    	};

						    										    								    	obj.update = function( id, record) {
						    										    								    		var url  = serviceBase + '/users/';
						    										    								    		return $http.put(url  + id,
						    										    								    				record);
						    										    								    	};

						    										    								    	obj.remove = function(id) {
						    										    								    		var url  = serviceBase + '/users/';
						    										    								    		return $http.delete(url + id);
						    										    								    	};

						    										    								    	return obj;
						    										    								    } ]).factory(
						    										    								    		'userRoleService',
						    										    								    		[
						    										    								    		 '$http',
						    										    								    		 function($http) {
						    										    								    			 var serviceBase = 'rest/v1.0/admin';
						    										    								    			 var obj = {};

						    										    								    			 obj.put = function(userid, roleid) {
						    										    								    				 var url  = serviceBase + '/users/'+userid+'/roles/'+roleid;
						    										    								    				 return $http.put(url);
						    										    								    			 };

						    										    								    			 obj.remove = function(userid, roleid) {
						    										    								    				 var url  = serviceBase + '/users/'+userid+'/roles/'+roleid;
						    										    								    				 return $http.delete(url);
						    										    								    			 };

						    										    								    			 return obj;
						    										    								    		 } ]).factory(
						    										    								    				 'expenseTypeService',
						    										    								    				 [
						    										    								    				  '$http',
						    										    								    				  function($http) {
						    										    								    					  var serviceBase = 'rest/v1.0/admin';
						    										    								    					  var obj = {};
						    										    								    					  obj.list = function( queries) {
						    										    								    						  var url  = serviceBase + '/expenseTypes/';
						    										    								    						  return $http.get(url, {params:queries});
						    										    								    					  };

						    										    								    					  obj.get = function( id) {
						    										    								    						  var url  = serviceBase + '/expenseTypes/';
						    										    								    						  return $http.get(url  + id);
						    										    								    					  };

						    										    								    					  obj.insert = function(record) {
						    										    								    						  var url  = serviceBase + '/expenseTypes/';
						    										    								    						  return $http.post(url, record);
						    										    								    					  };

						    										    								    					  obj.update = function( id, record) {
						    										    								    						  var url  = serviceBase + '/expenseTypes/';
						    										    								    						  return $http.put(url  + id,
						    										    								    								  record);
						    										    								    					  };

						    										    								    					  obj.remove = function(id) {
						    										    								    						  var url  = serviceBase + '/expenseTypes/';
						    										    								    						  return $http.delete(url + id);
						    										    								    					  };

						    										    								    					  return obj;
						    										    								    				  } ]).factory(
						    													    										    		'memberAuditAttendRecordService',
						    													    										    		[
						    													    										    		 '$http',
						    													    										    		 function($http) {
						    													    										    			 var serviceBase = 'rest/v1.0/users/';
						    													    										    			 var obj = {};
						    													    										    			
						    													    										    			 obj.get = function(userId, id) {
						    													    										    				 var url  = serviceBase + userId + '/auditAttendRecords/';
						    													    										    				 return $http.get(url  + id);
						    													    										    			 };

						    													    										    			 return obj;
						    													    										    		 } ]);