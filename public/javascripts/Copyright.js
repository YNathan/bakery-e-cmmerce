app.controller('Copyright', ['$scope', '$mdSidenav', 'ShamayimFunctions', '$state', '$http', '$rootScope', function ($scope, $mdSidenav, ShamayimFunctions, $state, $http, $rootScope) {


    $scope.userName = ShamayimFunctions.getCookie("username");
    $scope.isManager = function () {
        if (ShamayimFunctions.getPermissionCookie() == "0") {
            return true;
        } else {
            return false;
        }
    }

}]);
