'use strict';

var commonController = angular.module('commonController', []);

commonController.controller('commonController', ['$scope', 'commonService',
function ($scope, commonService) {

    ["recursive", "iterative", "matrix"].forEach( function (val, index, arr) {
        $scope[val] = {
            input: 1,
            result: 1,
            time: 0.0,
            loading: false,
            run: null
        };

        $scope[val].run = function() {

            $scope[val].loading = true;
            $scope[val].time = 0.0;
            var test = new Date()

            $scope[val].result = commonService[val]($scope[val].input).then(
                function (result) {
                    $scope[val].time = (new Date() - test) / 1000.0;
                    $scope[val].result = result.data;
                    $scope[val].loading = false;
                },
                function () {
                    $scope[val].result = 'Error';
                    $scope[val].loading = false;
                }
            );
        };
    });
}]);