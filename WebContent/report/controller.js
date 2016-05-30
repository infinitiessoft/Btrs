angular.module('reportApp.controller',[]).controller('ReportListController',function($scope,$state,popupService,$window,Report){

    $scope.report=Report.query();

    $scope.deleteReport=function(report){
        if(popupService.showPopup('Really delete this?')){
        	report.$delete(function(){
                $window.location.href='';
            });
        }
    }

}).controller('ReportViewController',function($scope,$stateParams,Report){

    $scope.report=Report.get({id:$stateParams.id});

}).controller('ReportCreateController',function($scope,$state,$stateParams,Report){

    $scope.report=new Report();

    $scope.addReport=function(){
        $scope.report.$save(function(){
            $state.go('report');
        });
    }

}).controller('ReportEditController',function($scope,$state,$stateParams,Report){

    $scope.updateReport=function(){
        $scope.report.$update(function(){
            $state.go('report');
        });
    };

    $scope.loadReport=function(){
        $scope.report=Report.get({id:$stateParams.id});
    };

    $scope.loadReport();
});