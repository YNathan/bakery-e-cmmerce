app.controller('house', ['$scope', '$http', '$state', '$interval', '$mdDialog', '$mdSidenav', 'ShamayimFunctions', '$rootScope', function ($scope, $http, $state, $interval, $mdDialog, $mdSidenav, ShamayimFunctions, $rootScope) {

    ShamayimFunctions.setIsLoggedCookie("true");
    $scope.houseWasLoaded = false;
    $scope.backToButton = "בחר בית";
    // Just print kind of 'hay message'
    $scope.message = 'M. ' + ShamayimFunctions.getCookie("username");
    $scope.house_id;
    $scope.state;
    $scope.city = "";
    $scope.street = "";
    $scope.house_number;
    $scope.house_kind;
    $scope.number_of_rooms;
    $scope.number_of_living_rooms;
    $scope.number_of_kitchens;
    $scope.number_of_bedrooms;
    $scope.number_of_bathrooms;
    $scope.location_kind;
    $scope.comments = "";
    $scope.purchase_price;
    $scope.treatment_fees;
    $scope.renovation_fees_for_sale;
    $scope.renovation_fees_for_renting;
    $scope.divers_fees;
    $scope.userName = ShamayimFunctions.getCookie("username");
    var houseName = "";
    var tempArr = [];
    $rootScope.dictionary;
    var results;
    // For the house
    $scope.houses = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            house: 'default'
        }
    };

    $rootScope.isManager = function () {
        if (ShamayimFunctions.getPermissionCookie() == "true") {
            return true;
        } else {
            return false;
        }
    }
    // Get information conserning the
    $http.get("/GET_LIST_OF_HOUSES")
        .then(function successCallback(response) {
                angular.forEach(response.data.houses, function (value, key) {
                    itemName = {
                        id: key,
                        house: value
                    }
                    tempArr.push(itemName);
                    $scope.houses.availableOptions.push(itemName.house);
                }, $scope.houses);
            },
            function error(response) {
                ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
            });

    function checkIfNeedConfirming() {
    }

    $interval(checkIfNeedConfirming, 200000);

    // For Images
    $scope.housePathesImages = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            imagesSource: 'default'
        }
    };

    $scope.houseImages = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            image: 'default'
        }
    };

    $scope.houseProfilePathesImages = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            imagesSource: 'default'
        }
    };

    $scope.houseDocumentsPathes = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            documnetsPath: 'default'
        }
    };

    var totalPages = 0;
    var totalDocumentPage = 0;

    function getHouseImages(nHouseId) {
        totalPages = 0;
        $scope.housePathesImages.availableOptions = [];
        $http.get('/GET_FILES_PATHS/' + nHouseId)
            .then(function successCallback(response) {
                    angular.forEach(response.data.files, function (value, key) {
                        itemName = {
                            id: key,
                            imagesSource: value
                        }
                        totalPages++;
                        $scope.housePathesImages.availableOptions.push(itemName.imagesSource);
                    }, $scope.housePathesImages);
                    $scope.pagingImage = {
                        total: totalPages,
                        current: 1,
                        onPageChanged: loadPagesImage,
                    };

                },
                function error(response) {
                    showAlert("Your attention please", response.data, "cant load houses");
                });
    }

    // Get Profile Images
    function getHouseProfileImages() {
        totalPages = 0;
        $http.get('/HOUSE_PICTURES_PROFILE_PATHES')
            .then(function successCallback(response) {
                    angular.forEach(response.data.files, function (value, key) {
                        itemName = {
                            id: key,
                            imagesSource: value
                        }
                        totalPages++;
                        $scope.houseProfilePathesImages.availableOptions.push(itemName.imagesSource);
                    }, $scope.houseProfilePathesImages);

                },
                function error(response) {
                    showAlert("Your attention please", response.data, "cant load houses");
                });
    }


    // Get Profile Images
    function getHouseDocumetns(nHouseId) {
        totalDocumentPage = 0;
        $scope.houseDocumentsPathes.availableOptions = [];
        $http.get('/HOUSE_DOCUMENTS_PATHES/' + nHouseId + '/' + ShamayimFunctions.getCookie("username"))
            .then(function successCallback(response) {
                    angular.forEach(response.data.files, function (value, key) {
                        itemName = {
                            id: key,
                            documnetsPath: value
                        }
                        totalDocumentPage++;
                        $scope.houseDocumentsPathes.availableOptions.push(itemName.documnetsPath);
                    }, $scope.houseDocumentsPathes);
                    $scope.pagingDocument = {
                        total: totalDocumentPage,
                        current: 1,
                        onPageChanged: loadPagesDocuments,
                    };
                },
                function error(response) {
                    showAlert("Your attention please", response.data, "cant load houses");
                });
    }

    getHouseProfileImages();


    // Logic methods section
    function getHouse(nHouseId) {
        // Get information conserning the house
        $http.get("/GET_HOUSE_BY_ID/" + nHouseId)
            .then(function successCallback(response) {
                    $scope.house_id = response.data.house.house_id;
                    $scope.state = response.data.house.state;
                    $scope.city = response.data.house.city;
                    $scope.street = response.data.house.street;
                    $scope.house_number = response.data.house.house_number;
                    $scope.house_kind = response.data.house.house_kind;
                    $scope.number_of_rooms = response.data.house.number_of_rooms;
                    $scope.number_of_living_rooms = response.data.house.number_of_living_rooms;
                    $scope.number_of_kitchens = response.data.house.number_of_kitchens;
                    $scope.number_of_bedrooms = response.data.house.number_of_bedrooms;
                    $scope.number_of_bathrooms = response.data.house.number_of_bathrooms;
                    $scope.location_kind = response.data.house.location_kind;
                    $scope.comments = response.data.house.comments;
                    $scope.purchase_price = response.data.house.purchase_price;
                    $scope.treatment_fees = response.data.house.treatment_fees;
                    $scope.renovation_fees_for_sale = response.data.house.renovation_fees_for_sale;
                    $scope.renovation_fees_for_renting = response.data.house.renovation_fees_for_renting;
                    $scope.divers_fees = response.data.house.divers_fees;
                    newMapLocation($scope.house_number, $scope.street, $scope.city, $scope.state);
                    $scope.houseWasLoaded = true;
                    // Get images
                    $scope.houseImages.availableOptions = [];
                    $scope.housePathesImages.availableOptions = [];
                    $scope.houseDocumentsPathes.availableOptions = [];
                    getHouseImages(nHouseId);
                    getHouseDocumetns(nHouseId);

                },
                function error(response) {
                    ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
                });


    }


    function getHouseByAddress(szHouseAddressProfilePath) {
        // Get information conserning the house
        $http.get("/GET_HOUSE_BY_ADDRESS_PROFILE_PATH/" + szHouseAddressProfilePath)
            .then(function successCallback(response) {
                    $scope.house_id = response.data.house.house_id;
                    $scope.state = response.data.house.state;
                    $scope.city = response.data.house.city;
                    $scope.street = response.data.house.street;
                    $scope.house_number = response.data.house.house_number;
                    $scope.house_kind = response.data.house.house_kind;
                    $scope.number_of_rooms = response.data.house.number_of_rooms;
                    $scope.number_of_living_rooms = response.data.house.number_of_living_rooms;
                    $scope.number_of_kitchens = response.data.house.number_of_kitchens;
                    $scope.number_of_bedrooms = response.data.house.number_of_bedrooms;
                    $scope.number_of_bathrooms = response.data.house.number_of_bathrooms;
                    $scope.location_kind = response.data.house.location_kind;
                    $scope.comments = response.data.house.comments;
                    $scope.purchase_price = response.data.house.purchase_price;
                    $scope.treatment_fees = response.data.house.treatment_fees;
                    $scope.renovation_fees_for_sale = response.data.house.renovation_fees_for_sale;
                    $scope.renovation_fees_for_renting = response.data.house.renovation_fees_for_renting;
                    $scope.divers_fees = response.data.house.divers_fees;
                    newMapLocation($scope.house_number, $scope.street, $scope.city, $scope.state);
                    $scope.houseWasLoaded = true;
                    // Get images
                    $scope.houseImages.availableOptions = [];
                    getHouseImages($scope.house_id);
                    // Get documents
                    $scope.houseDocumentsPathes.availableOptions = [];
                    getHouseDocumetns($scope.house_id)
                },
                function error(response) {
                    ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
                });


    }

    $scope.sendMeMail = function (szHouseId) {
        // Get information conserning the house
        $http.get("/SEND_ME_THE_HOUSE/" + ShamayimFunctions.getCookie("username") + "/" + szHouseId)
            .then(function successCallback(response) {
                    ShamayimFunctions.showAlert("Operation Success Open You Mail To See The House", response.data, "");
                },
                function error(response) {
                    ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
                });
    }

    $scope.deleteHouse = function(ev,szHouseId) {
        // Appending dialog to document.body to cover sidenav in docs app
        var confirm = $mdDialog.confirm()
            .title('Would you like to delete the house?')
            .textContent('All the details and the files will be deleted .')
            .ariaLabel('Lucky day')
            .targetEvent(ev)
            .ok('I Agree')
            .cancel('I Dont Want');

        $mdDialog.show(confirm).then(function() {
            $http.post("/DELETE_HOUSE/" + ShamayimFunctions.getCookie("username") + "/" + szHouseId)
                .then(function successCallback(response) {
                        ShamayimFunctions.showAlert("Operation Success", response.data, "");
                    },
                    function error(response) {
                        ShamayimFunctions.showAlert("Your attention please", response.data, "cant delete house");
                    });
        }, function() {
        });
    };




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
                    $scope.backToButton = $rootScope.dictionary.Dictionary[0].SelectaHouse;
                },
                function error(response) {
                    ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
                });
        ShamayimFunctions.setLanguageCookie(szLanguageName);

    }

    var languageToGet = ShamayimFunctions.setLanguageCookie();

    $rootScope.$watch('Languages.selectedOption', function (newVal, oldVal) {
        if (newVal != oldVal) {
            HouseLanguageName = newVal;
            getLanguage(newVal);

        }
    })

    // End Of Language Section

    $scope.houseWasChosen = false;

    $scope.houseClicked = function (src) {
        getHouseByAddress(src);
        $scope.houseProfilePathesImages.availableOptions = [];
        $scope.houseWasLoaded = true;
        $scope.backToButton = $rootScope.dictionary.Dictionary[0].BackToTheCatalog;
    }

    $scope.backToTheCatalog = function (src) {
        $scope.houseWasLoaded = false;
        $scope.backToButton = $rootScope.dictionary.Dictionary[0].SelectaHouse;
        getHouseProfileImages();

    }

    // Just check if there is a user name
    if (ShamayimFunctions.getCookie("username") == null) {
        // Go to the main application
        $state.go('wellcom');
    }
    getLanguage("עברית");
    $scope.$watch('houses.selectedOption', function (newVal, oldVal) {
        if (newVal != oldVal) {
            houseName = newVal;
            getHouse(newVal.house_id);

        }
    })


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


    $scope.currentPageImage = 0;
    $scope.pagingImage = {
        total: totalPages,
        current: 1,
        onPageChanged: loadPagesImage,
    };
    function loadPagesImage() {
        console.log('Current image page is : ' + $scope.pagingImage.current);
        $scope.currentPageImage = $scope.pagingImage.current;
        $scope.houseImage = $scope.housePathesImages.availableOptions[$scope.currentPageImage - 1];
    }

    $scope.currentPageDocuments = 0;
    $scope.pagingDocument = {
        total: totalDocumentPage,
        current: 1,
        onPageChanged: loadPagesDocuments,
    };
    function loadPagesDocuments() {
        console.log('Current document page is : ' + $scope.pagingDocument.current);
        $scope.currentPageDocuments = $scope.pagingDocument.current;
        $scope.houseDocument = $scope.houseDocumentsPathes.availableOptions[$scope.currentPageDocuments - 1];
    }
}])

