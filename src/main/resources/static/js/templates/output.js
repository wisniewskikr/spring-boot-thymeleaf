angular.module('app', [])
.controller('ctrl', function($scope, $http, $window) {
	
	var url = "http://localhost:8080/user/show";
	
    $http.get(url).
        then(function(response) {
            $scope.name = response.data.name;
        },
		function(errResponse) {
			alert(errResponse);
		}
    );

    
});