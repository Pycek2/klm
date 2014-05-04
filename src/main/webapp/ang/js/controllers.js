hms.controller('homePage',['$scope' ,'$http','$routeParams', '$location', '$cookies', function($scope, $http, $routeParams, $location, $cookies){
	$scope.changeView = function(view){
		$location.path(view); // path not hash
	}
	$http.post('api/data/public').success( function (data) {
		$scope.sensors = data;
	});
	$scope.loginerror = '';
	$scope.login = function(){
		$http.post('api/admin/users/verify', $scope.loginuser)
	    .success(function(data) {
	    	console.log("data",data);
	    	if (data.result) {
	    		$scope.loginerror = '';
	    		$cookies.logged = true;
	    		$cookies.firstName = data.firstName;
	    		$cookies.lastName = data.lastName;
	    		$cookies.role = data.role;
	    		$location.path('sensors');
	    	}
	    	else {
	    		console.log("złe dane");
	    		$scope.loginerror = "Błędne dane."
	    	}
//	    	$location.path('users');
	    });
	}
}]);

hms.controller('sensors',['$scope','$http','$routeParams',function($scope, $http, $routeParams){
	$http.post('api/data/latest').success( function (data) {
		$scope.sensors = data;
	});
}]);

hms.controller('tabs',['$scope','$http','$routeParams', '$cookies' , function($scope, $http, $routeParams, $cookies){
	console.log("kontroler tabsów");
	$scope.role = $cookies.role;
	if($cookies.role == 'ADMIN') {
		console.log("tak");
		$scope.userManager = true;
	}
	else {
		console.log("nie");
		$scope.userManager = false;
	}
	
	
}]);

hms.controller('topBar',['$scope','$http','$routeParams', '$location' , '$cookies' ,function($scope, $http, $routeParams, $location, $cookies){
	$scope.firstName = $cookies.firstName;
	$scope.lastName = $cookies.lastName;
	
	$scope.logout = function(){
		 $cookies.firstName = '';
		 $cookies.lastName = '';
		 $cookies.logged = false;
		 $location.path('/');
	}
}]);

hms.controller('users',['$scope','$http','$routeParams', '$location',  function($scope, $http, $routeParams, $location){
	$http.get('api/admin/users/').success( function (data) {
		$scope.userlist = data;
	});
	$scope.changeView = function(view){
		$location.path(view); // path not hash
	};
	$scope.removeUser = function(id){
		$http.delete('api/admin/users/'+id)	
		.success(function(data) {
			$http.get('api/admin/users/').success( function (data) {
				$scope.userlist = data;
			});
	    });
			
	   
	};
	$scope.blockUser = function(id){
		$http.get('api/admin/users/disable/'+id)
		.success(function(data) {
			$scope.changeView('users');
	    });
	};
}]);

hms.controller('newUser',['$scope','$http','$routeParams', '$location', function($scope, $http, $routeParams, $location){
	$scope.changeView = function(view){
		$location.path(view); // path not hash
	};
	
//	$scope.newuser.role = 'browser';
	
	$scope.newUserSubmit = function(){
		console.log("post",$scope.newuser);
		$http.post('api/admin/users/0', $scope.newuser)
	    .success(function(data) {
	    	$location.path('users');
	    });
	    
	}
}]);

hms.controller('editUser',['$scope','$http','$routeParams', '$location', function($scope, $http, $routeParams, $location){
		$scope.changeView = function(view){
			$location.path(view); // path not hash
		};
		$scope.firstName = "konrad";
		$scope.lastName = "kruk";
		$scope.email = "kontakt@konradk.pl";
		$scope.login = "konradk";
		$scope.password = "haslohaslo";

}]);

hms.controller('tabs',['$scope','$http','$routeParams', '$location', function($scope, $http, $routeParams, $location){
	$scope.changeView = function(view){
		$location.path(view); // path not hash
	}
}]);