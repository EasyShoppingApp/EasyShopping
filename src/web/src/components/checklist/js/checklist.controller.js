'use strict';

angular.module('shoptrip').directive('ngEnter', function () {
  return function (scope, element, attrs) {
    element.bind("keydown keypress", function (event) {
      if (event.which === 13) {
        scope.$apply(function () {
          scope.$eval(attrs.ngEnter);
        });
        event.preventDefault();
      }
    });
  };
});

angular.module('shoptrip')
.controller('CheckListCtrl', ['$scope', 'require', function ($scope, require){
  // console.log(require);
  
  $scope.checkList = [
  {text: 'Cucumber', done: false},
  {text: 'Beer', done: false}
  ];

  $scope.getTotalCheckList = function () {
    var count = 0;
    $scope.checkList.forEach(function (i) {
      if (!i.done)
        count++;
    });

    if (count > 0)
      return count;

    else if ($scope.checkList.length > 0) {
      return 0;
    }

    else {
      return null;
    }
  };

  var callback = function(imgUrl){
      require(["map"], function(module){
        module.AddPoint(
          Math.floor(Math.random() * 90), 
          Math.floor(Math.random() * 90),
          imgUrl);
      });
  }

  $scope.addItem = function () {
    if ($scope.formItemText) {
      $scope.checkList.push({text: $scope.formItemText, done: false});
      
      var textToSearch = $scope.formItemText;

      require(["imageApi"], function(imageApi){
        imageApi.AddToBody(textToSearch, 0, callback);
      });

      $scope.formItemText = '';
    }
    else {
        //TODO 18-Oct-2014: show popup saying e.g "Please type your item name"
      }
    };

    $scope.removeItem = function (index) {
      if (index === 'underfined') {
        $scope.checkList = _.filter($scope.checkList, function (item) {
          return !item.done;
        });
      }

      else {
        $scope.checkList.splice(index, 1);
      }
    };

    $scope.toggleItem = function (index) {
      $scope.checkList[index].done = !$scope.checkList[index].done;
    };

  }]);