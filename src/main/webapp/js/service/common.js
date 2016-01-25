'use strict';

/* Services */

var commonService = angular.module('commonService', []);

commonService.factory('commonService', [ '$http', '$q',
  function ( $http, $q ) {
    var service = {
        matrix: null,
        recursive: null,
        iterative: null
    };

    service.matrix = function(n) {
        return $http.get('../fib/api/matrix/' + n);
    }

    service.recursive = function(n) {
        return $http.get('../fib/api/recursive/' + n);
    }

    service.iterative = function(n) {
        return $http.get('../fib/api/iterative/' + n);
    }

    return service;
  }
]);