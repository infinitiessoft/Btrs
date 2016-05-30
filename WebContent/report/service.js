angular.module('reportApp.service',[]).factory('Report',function($resource){
    return $resource('rest/report:id',{id:'@_id'},{
        update: {
            method: 'PUT'
        }
    });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});