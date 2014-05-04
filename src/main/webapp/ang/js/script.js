var hms = angular.module('hms',['ngRoute', 'ngCookies']);

hms.config(['$routeProvider',function($routeProvider){
	$routeProvider.
		when('/',{
			controller: "homePage",
			templateUrl: "ang/partials/homePage.html"
		}).
		when('/users',{
			controller: "users",
			templateUrl: "ang/partials/users.html"
		}).
		when('/user/new',{
			controller: "newUser",
			templateUrl: "ang/partials/newUser.html"
		}).
		when('/user/edit/:id',{
			controller: "editUser",
			templateUrl: "ang/partials/newUser.html"
		}).
		when('/sensors',{
			controller: "sensors",
			templateUrl: "ang/partials/sensors.html"
		});
}]);