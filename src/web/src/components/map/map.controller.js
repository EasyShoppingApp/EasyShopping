'use strict';

angular.module('shoptrip')
	.controller('MapCtrl', ['$scope', function ($scope){

		$scope.test = 123;
		require(['map'], function(map){
            $scope.$on('delete', function (event, args) {
                debugger;
            });

            $scope.$on('add', function (event, args) {
                debugger;
            });

            $scope.$on('toggle', function (event, args) {
                debugger;
            });

            $scope.$on('list', function (event, args) {
                map.JsonToMap(args);
            });
            map.Init($scope);
        });
	}]);