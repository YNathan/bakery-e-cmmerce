app.controller('loginRegister', ['$scope', '$http', '$filter', '$state', '$mdDialog', 'ShamayimFunctions', '$mdToast', '$rootScope', function ($scope, $http, $filter, $state, $mdDialog, ShamayimFunctions, $mdToast, $rootScope) {
    $scope.userName = 'RobertDupont';
    $scope.firstName = "Robert";
    $scope.lastName = "Dupont";
    $scope.telephone;
    $scope.email = "Robert@gmail.com";
    $scope.password = "a";
    $scope.birthdate = 1 - 1 - 2001;

    // For login scope
    $scope.Username = 'Y.Nathan';
    $scope.Password = 'a';
    ShamayimFunctions.setIsLoggedCookie("false")

    $scope.login = function () {
        var userName = $scope.Username;
        var password = $scope.Password;

        // Check if the name exist
        $http({
            method: 'POST',
            url: '/LOGIN/' + userName + '/' + password
        }).then(function successCallback(response) {
            var szPermission = response.data;
            if (szPermission != "-1") {
                ShamayimFunctions.setUserNameCookie("username", userName);
                ShamayimFunctions.setPermissionCookie(szPermission);
                ShamayimFunctions.setIsLoggedCookie("true");
                closeAlert();

                // Go to the main application
                $state.go('House');
            }
        }, function error(response) {
            ShamayimFunctions.showAlert("כניסה למערכת", "הכנס שם משתמש וסיסמא תקניים", "נכשל");
        });
    };

    // Dialog
    $scope.status = '  ';
    $scope.customFullscreen = false;

    $rootScope.showAdvanced = function (ev) {
        $mdDialog.show({
            controller: 'loginRegister',
            templateUrl: 'template/lr.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true,
            flex: 0,
        })
            .then(function (answer) {
                $scope.status = 'You said the information was "' + answer + '".';
            }, function () {
                $scope.status = 'You cancelled the dialog.';
            });
    };

    function closeAlert() {
        $mdDialog.hide(alert, "finished");
        alert = undefined;
    }

    function DialogController($scope, $mdDialog) {
        $scope.hide = function () {
            $mdDialog.hide();
        };

        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    }


    $rootScope.isLoged = function () {
        if (ShamayimFunctions.getIsLoggedCookie() == "true") {
            return true
        } else {
            return false;
        }
    }
}]);
