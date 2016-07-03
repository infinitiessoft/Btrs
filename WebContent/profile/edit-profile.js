angular.module('edit-profile', [ 'formly', 'formlyBootstrap' ]).config(
		function config(formlyConfigProvider, formlyExampleApiCheck) {
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
		}).constant('formlyExampleApiCheck', apiCheck()).controller(
		'edit-profile',
		function($rootScope, $scope, $stateParams, $state, $translate,
				formlyVersion, profileService) {
			var id = $stateParams.userid;
			$rootScope.title = 'Profile'
			$rootScope.buttonText = 'Leave';
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

			// if you must support IE8 this might work with the
			// es5-shim: https://github.com/es-shims/es5-shim
			Object.defineProperty(vm.options.formState, 'lang', {
				enumerable : true,
				get : function() {
					console.log('getting ' + $translate.use());
					return $translate.use();
				},
				set : function(arg) {
					console.log('setting ' + arg);
					return $translate.use(arg);
				}
			});

			vm.model = {};
			vm.jobTitle = {};
			profileService.get(id).then(
					function(status) {
						vm.model = status.data;
						vm.model.gender = $translate.instant(vm.model.gender);
						vm.jobTitle.name = $translate.instant(status.data.jobTitle.name);
					});


			vm.fields = [ {
				key : 'name',
				type : 'input',
				templateOptions : {
					label : $translate.instant('Name'),
					placeholder : 'Name',
					type : 'text',
					disabled : true
				}
			}, {
				key : 'name',
				type : 'input',
				model : vm.jobTitle,
				templateOptions : {
					label : $translate.instant('Job Title'),
					placeholder : 'jobTitle',
					type : 'text',
					disabled : true
				}
			}, {
				key : 'username',
				type : 'input',
				templateOptions : {
					label : $translate.instant('Username'),
					placeholder : 'Username',
					type : 'text',
					disabled : true
				}
			}, {
				key : 'email',
				type : 'input',
				templateOptions : {
					label : $translate.instant('Email'),
					placeholder : 'E-mail',
					type : 'email',
					disabled : true
				}
			}, {
				key : 'gender',
				type : 'input',
				templateOptions : {
					label : $translate.instant('Gender'),
					placeholder : 'Gender',
					type : 'input',
					disabled : true
				}
			}, {
				key : 'dateofjoined',
				type : 'input',
				templateOptions : {
					label : $translate.instant('Created Date'),
					placeholder : 'Created Date',
					type : 'input',
					disabled : true
				}
			} ];
			function onSubmit() {
				$state.go('dashboard.home');
			}

		});