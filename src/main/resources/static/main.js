var app = angular.module("EmailService", []);

// Controller Part
app.controller("RestController", function($scope, $http) {
    $scope.paymentForm = {
        //currentDate: currentDate(),
        empNo: "",
        empName: ""
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

    function _success(res) {
        _clearFormData();
    }

    function _error(res) {
        var data = res.data;
        var status = res.status;
        alert("Error: " + status + ":" + data);
    }
});