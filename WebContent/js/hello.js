angular
		.module('hello', [ 'ui.router', 'auth', 'navigation' ])
		.config(
				[
						'$stateProvider',
						'$urlRouterProvider',
						'$httpProvider',
						function($stateProvider, $urlRouterProvider,
								$httpProvider) {

							$urlRouterProvider.otherwise('/home');

							$stateProvider.state('login', {
								url : '/login',
								controller : 'navigation',
								templateUrl : 'login.html'
							}).state('home', {
								url : '/home',
								templateUrl : 'pages/homescreen.html'
							}).state('about', {
								url : '/about',
								controller : 'aboutController',
								templateUrl : 'about.html'
							}).state('create', {
								url : '/create',
								controller : 'createController',
								templateUrl : 'createReport/createReport.html'
							}).state('report', {
								url : '/report',
								controller : 'reportController',
								templateUrl : 'report.html'
							}).state('expense', {
								url : '/expense',
								controller : 'expenseController',
								templateUrl : 'expense/expense.html'
							}).state('photo', {
								url : '/photo',
								controller : 'photoController',
								templateUrl : 'expense/photo.html'
							}).state('category', {
								url : '/category',
								controller : 'categoryController',
								templateUrl : 'expenseCategory/eCategory.html'
							}).state('type', {
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
						} ]).factory(
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
						} ]).run(function($rootScope, $http, auth) {
			auth.init('/home', '/login', 'logout');

			/* Reset error when a new view is loaded */
			$rootScope.$on('$viewContentLoaded', function() {
				delete $rootScope.error;
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
		});