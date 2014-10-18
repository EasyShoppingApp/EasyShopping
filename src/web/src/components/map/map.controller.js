'use strict';

angular.module('shoptrip')
	.controller('MapCtrl', ['$scope', function ($scope){
		$scope.test = 123;
		require(['map']);
	}]);