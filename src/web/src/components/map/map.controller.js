'use strict';

angular.module('shoptrip')
	.controller('MapCtrl', ['$scope', function ($scope){

		$scope.test = 123;
		require(['map'], function(map){
            $scope.$on('delete', function (event, product) {
                map.DeletePoint(product)
            });

            $scope.$on('add', function (event, product) {
                map.AddPoint(product);
            });

            $scope.$on('toggle', function (event, args) {
                map.TogglePoint(args);
            });

            $scope.$on('list', function (event, args) {
                map.JsonToMap(args);
            });
            map.Init($scope);
        });
	}]);