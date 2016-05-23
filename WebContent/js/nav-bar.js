App.directive('navBar', function () {
    'use strict';
    
    var html = "<nav class=\"navbar navbar-default\" role=\"navigation\">" +
               "    <div class=\"container\">" +
               "        <!-- Brand and toggle get grouped for better mobile display -->" +
               "        <div class=\"navbar-header\">" +
               "            <button type=\"button\" class=\"navbar-toggle collapsed\">" +
               "                <i class=\"fa fa-navicon\"></i>" +
               "            </button>" +
               "            <a class=\"navbar-brand\" href=\"#\">My Brand</a>" +
               "        </div>" +
               "" +
               "        <!-- Collect the nav links, forms, and other content for toggling -->" +
               "        <div class=\"collapse navbar-collapse\">" +
               "            <ul class=\"nav navbar-nav\">" +
               "                <li class=\"active\"><a ui-sref=\"app.home\">Home</a></li>" +
               "                <li><a ui-sref=\"app.settings\">Settings</a></li>" +
               "                <li class=\"dropdown\">" +
               "                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Dropdown <span class=\"caret\"></span></a>" +
               "                    <ul class=\"dropdown-menu\" role=\"menu\">" +
               "                        <li><a href=\"#\">Action</a></li>" +
               "                        <li><a href=\"#\">Another action</a></li>" +
               "                        <li><a href=\"#\">Something else here</a></li>" +
               "                        <li class=\"divider\"></li>" +
               "                        <li><a href=\"#\">Separated link</a></li>" +
               "                        <li class=\"divider\"></li>" +
               "                        <li><a href=\"#\">One more separated link</a></li>" +
               "                    </ul>" +
               "                </li>" +
               "            </ul>" +
               "        </div>" +
               "    </div>" +
               "</nav>";

    return {
        restrict: 'A',
        template: html,
        link: function (scope, element, $attrs) {

        }
    };

});
