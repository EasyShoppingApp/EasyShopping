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

angular.module('shoptrip').controller('CheckListCtrl', ['$scope', 'require', function ($scope, require) {
    // console.log(require);

    $scope.$on('delete', function (event, args) {
        angular.forEach($scope.checkList,function(item) {
            if (item._id.$oid === args._id.$oid) {
                $scope.checkList.splice($scope.checkList.indexOf(item), 1);
                $scope.$apply();
            }
        });
    });
    $scope.$on('add', function (event, args) {
        $scope.checkList.push(args);
        $scope.$apply();
    });
    $scope.$on('toggle', function (event, args) {
        angular.forEach($scope.checkList,function(item) {
            if (item._id.$oid === args._id.$oid) {
                item.done = args.done;
                $scope.$apply();
            }
        });
    });
    $scope.$on('list', function (event, args) {
        $scope.checkList.splice(0, $scope.checkList.length);
        angular.forEach(args,function(item) {
            $scope.checkList.push(item);
        });
        $scope.$apply();
    });

    $scope.checkList = [
        {productName: 'Cucumber', done: false},
        {productName: 'Beer', done: false}
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

    var addCallback = function (item) {
        globalsocket.add({productName: item.name, done: false, imgUrl: item.imgUrl});
        /*require(["map"], function (module) {
            module.AddPoint(
                Math.floor(Math.random() * 90),
                Math.floor(Math.random() * 90),
                item.imgUrl);
        });*/
    }

    $scope.addItem = function () {
        if ($scope.formItemText) {
            var textToSearch = $scope.formItemText;
            require(["imageApi"], function (imageApi) {
                imageApi.getImgUrl(textToSearch, 0, addCallback);
            });
            //globalsocket.add({productName: item.name, done: false, imgUrl: item.imgUrl});
            $scope.formItemText = '';
        }
        else {
            //TODO 18-Oct-2014: show popup saying e.g "Please type your item name"
        }
    };

    $scope.removeItem = function (index) {
        globalsocket.delete($scope.checkList[index]);
        /*if (index === 'undefined') {
            $scope.checkList = _.filter($scope.checkList, function (item) {
                return !item.done;
            });
        }

        else {
            $scope.checkList.splice(index, 1);
        }*/
    };

    $scope.toggleItem = function (index) {
        var item = $scope.checkList[index];
        globalsocket.toggle(item);
    };

}]);