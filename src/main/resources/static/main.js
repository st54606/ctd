var app = angular.module("EmailService", []);

app.controller("RestController", function($scope, $http) {
    $scope.paymentForm = {
        currentDate: new Date()
    };

    $scope.sendEmail = function() {
            method = "POST";
            url = '/sendEmail';

        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.paymentForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    function _success() {
        alert("Successfully done");
    }

    function _error() {
        alert("Something went wrong");
    }

});