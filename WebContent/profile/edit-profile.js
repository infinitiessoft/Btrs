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