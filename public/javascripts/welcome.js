app.controller('welcome', ['$scope', '$http', '$state', '$interval', '$mdDialog', '$mdSidenav', 'ShamayimFunctions', '$rootScope', function ($scope, $http, $state, $interval, $mdDialog, $mdSidenav, ShamayimFunctions, $rootScope) {

    $scope.lala = "poulet roty";
    ShamayimFunctions.setIsLoggedCookie("false");
    // Language Section

    $rootScope.Languages = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            HouseLanguage: 'default'
        }
    };

    $rootScope.Languages = ShamayimFunctions.getExistingLanguages();

    function getLanguage(szLanguageName) {
        // Get information conserning the house
        $http.get("/GET_LANGUAGE/" + szLanguageName)
            .then(function successCallback(response) {
                    $rootScope.dictionary = response.data;
                    $rootScope.pageDirection = $rootScope.dictionary.Dictionary[0].PageDirection;
                },
                function error(response) {
                    ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
                });
        ShamayimFunctions.setLanguageCookie(szLanguageName);

    }


    getLanguage("עברית");

    $rootScope.$watch('Languages.selectedOption', function (newVal, oldVal) {
        if (newVal != oldVal) {
            HouseLanguageName = newVal;
            getLanguage(newVal);

        }
    })

    // End Of Language Section

    // Repas Section
    $scope.foodProfilePathesImages = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            imagesSource: 'default'
        }
    };


    // Get Profile Images
    function getFoodProfileImages(foodName) {
        $http.get('/GET_FOOD/'+foodName)
            .then(function successCallback(response) {
                    $scope.foodProfilePathesImages.availableOptions = [];
                    angular.forEach(response.data, function (value, key) {
                        itemName = {
                            id: key,
                            imagesSource: value
                        }
                        $scope.foodProfilePathesImages.availableOptions.push(itemName.imagesSource);
                    }, $scope.foodProfilePathesImages);

                },
                function error(response) {
                    showAlert("Your attention please", response.data, "cant load food");
                });
    }

    $scope.Repas = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            RepasHPB: 'default'
        }
    };

    var tempArr = [];
    itemName = {
        id: 1,
        RepasHPB: "Bessari"
    }
    tempArr.push(itemName);
    $scope.Repas.availableOptions.push(itemName.RepasHPB);
    itemName = {
        id: 2,
        RepasHPB: "Parve"
    }
    tempArr.push(itemName);
    $scope.Repas.availableOptions.push(itemName.RepasHPB);
    itemName = {
        id: 3,
        RepasHPB: "Halavi"
    }
    tempArr.push(itemName);

    $scope.Repas.availableOptions.push(itemName.RepasHPB);
    function getLanguage(szLanguageName) {
        // Get information conserning the house
        $http.get("/GET_LANGUAGE/" + szLanguageName)
            .then(function successCallback(response) {
                    $rootScope.dictionary = response.data;
                    $rootScope.pageDirection = $rootScope.dictionary.Dictionary[0].PageDirection;
                },
                function error(response) {
                    ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
                });
        ShamayimFunctions.setLanguageCookie(szLanguageName);

    }


    getLanguage("עברית");

    $scope.$watch('Repas.selectedOption', function (newVal, oldVal) {
        if (newVal != oldVal) {
            RepasName = newVal;
            getFoodProfileImages(RepasName)

        }
    })



// End Of Getting Repas Section


    $rootScope.toggleLeft = function () {
        $mdSidenav('left').toggle();
    }
    $rootScope.goToCopyright = function () {
        $state.go('Copyright');
    }
    $rootScope.goToHouses = function () {
        $state.go('Houses');
    }
    $rootScope.goToNewHouse = function () {
        $state.go('NewOrEditHouse');
    }
    $rootScope.goToSystemManager = function () {
        $state.go('Manager');
    }
    $rootScope.goToHouse = function () {
        $state.go('House');
    }
    $rootScope.goToHouse = function () {
        $state.go('House');
    }
    $rootScope.logout = function () {
        ShamayimFunctions.setIsLoggedCookie("false");
        $state.go('welcome');

    }
    $rootScope.showLrButton = function () {
        if (ShamayimFunctions.getIsLoggedCookie() == "true") {
            return false
        } else {
            return true;
        }
    }

// Dialog
    $scope.status = '  ';
    $scope.customFullscreen = false;

    $rootScope.Copyright = function (ev) {
        $mdDialog.show({
            controller: 'Copyright',
            templateUrl: 'template/Copyright.html',
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
newMapLocation(12,'BOGRASHOV','tel aviv','israel')
    function newMapLocation(nNumberOfHouse, szStreetName, szCityName, szStateName) {
        var longitude = 0.0;
        var latitude = 0.0;

        // Get information conserning the house
        $http.get("https://maps.googleapis.com/maps/api/geocode/json?address=" + nNumberOfHouse + "," + szStreetName + "," + szCityName + "," + szStateName + "&key=AIzaSyDiEizMb95sLkzDtvQ-eRoZb-LzYmv2VVI")
            .then(function successCallback(response) {
                    latitude = response.data.results[0].geometry.location.lat;
                    longitude = response.data.results[0].geometry.location.lng;
                    var uluru = {
                        lat: latitude,
                        lng: longitude
                    };
                    var map = new google.maps.Map(document.getElementById('map'), {
                        zoom: 15,
                        center: uluru
                    });
                    var marker = new google.maps.Marker({
                        position: uluru,
                        map: map
                    });
                },
                function error(response) {
                    ShamayimFunctions.showAlert("Your attention please", response.data, "cant load maps");
                });

    }
}]).directive('lazyLoad', function ($timeout) {
    return {
        restrict: 'A',
        scope: {},
        link: function (scope, elem, attrs) {
            $timeout(function () {
                elem.attr('src', attrs.llSrc)
            });
        },
    }
});
