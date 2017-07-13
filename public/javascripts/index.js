app.controller('index', ['$scope', '$http', '$filter', '$state', '$mdDialog', '$mdSidenav', function ($scope, $http, $filter, $state, $mdDialog, $mdSidenav) {

    $scope.isManager = function () {
            if (ShamayimFunctions.getPermissionCookie() == "0") {
                return true;
            } else {
                return false;
            }
        }
        // Get value from the cookie
    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1);
            if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
        }
        return "";
    }

    function showAlert(title, textContent, ariaLabel) {
        // Appending dialog to document.body to cover sidenav in docs app
        // Modal dialogs should fully cover application
        // to prevent interaction outside of dialog
        $mdDialog.show(
            $mdDialog.alert()
            .parent(angular.element(document.querySelector('#popupContainer')))
            .clickOutsideToClose(true)
            .title(title)
            .textContent(textContent)
            .ariaLabel(ariaLabel)
        );
    }


    // Will input the user name into the cookie
    function setUserNameCookie(szName, szValue) {
        document.cookie = szName + "=" + szValue + "; ";
    }

    $scope.uploadFile = function (files) {
        var fd = new FormData();
        //Take the selected file
        fd.append("file", files[0]);

        $http.post("/upload/" + getCookie("username"), fd, {
            withCredentials: true,
            headers: {
                'Content-Type': undefined
            },
            transformRequest: angular.identity
        }).success(
            swal("Yeah!!!")
        ).error(
            swal("Oups! something wrong was hapening")
        );

    };

    $scope.toggleLeft = function () {
        $mdSidenav('left').toggle();
    }
    $scope.goToCopyright = function () {
        $state.go('Copyright');
    }
    $scope.goToUserInformation = function () {
        $state.go('userInformation');
    }
    $scope.goToHouses = function () {
        $state.go('Houses');
    }
    $scope.goToNewHouse = function () {
        $state.go('NewOrEditHouse');
    }
    $scope.goToHouse = function () {
        $state.go('House');
    }

}]);
