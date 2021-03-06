angular
		.module('auth', [])
		.factory(
				'auth',
				function($rootScope, $http, $location) {

					enter = function() {
						if ($location.path() != auth.loginPath) {
							auth.path = $location.path();
							if (!auth.authenticated) {
								$location.path(auth.loginPath);
							}
						}
					}

					var auth = {
						authenticated : false,
						user : {},
						loginPath : '/login',
						logoutPath : 'logout',
						homePath : '/home',
						path : $location.path(),

						authenticate : function(credentials, callback) {

							var headers = credentials && credentials.username ? {
								authorization : "Basic "
										+ btoa(credentials.username + ":"
												+ credentials.password)
							}
									: {};

							$http
									.get('rest/v1.0/auth', {
										headers : headers
									})
									.success(
											function(data) {
												if (data.name) {
													auth.user = data;
													auth.authenticated = true;
													callback
															&& callback(auth.authenticated);
													$location
															.path(auth.path == auth.loginPath ? auth.homePath
																	: auth.path);
												} else {
													auth.authenticated = false;
													callback
															&& callback(auth.authenticated);
													$location
															.path(auth.loginPath);
												}

											}).error(function() {
										auth.authenticated = false;
										auth.user = {};
										callback && callback(false);
									});

						},

						clear : function() {
							$location.path(auth.loginPath);
							auth.authenticated = false;
							auth.user = {};
							$http.post(auth.logoutPath, {}).success(function() {
								console.log("Logout succeeded");
								$location.path(auth.loginPath);
							}).error(function(data) {
								console.log("Logout failed");
								$location.path(auth.loginPath);
							});

						},

						init : function(homePath, loginPath, logoutPath) {

							auth.homePath = homePath;
							auth.loginPath = loginPath;
							auth.logoutPath = logoutPath;

							auth.authenticate({}, function(authenticated) {
								if (authenticated) {
									$location.path(auth.path);
								}
							})

							

						}

					};

					// Guard route changes and switch to login page if
					// unauthenticated
					$rootScope.$on('$viewContentLoaded',
							function(event) {
								if (!auth.authenticated) {
									event.preventDefault();
									$location.path(auth.loginPath);
								} else {
									enter();
								}
							});
					
					 $rootScope.$on('$locationChangeStart', function (event, next, current) {
							if (!auth.authenticated) {
								$location.path(auth.loginPath);
							} else {
								enter();
							}
				        });
					
					return auth;

				});
