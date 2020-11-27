app.controller('uploadfood', ['$scope', '$http', '$state', '$interval', '$mdDialog', '$mdSidenav', 'ShamayimFunctions', '$rootScope', function ($scope, $http, $state, $interval, $mdDialog, $mdSidenav, ShamayimFunctions, $rootScope) {
    $scope.strCaptionDragAndDrop = "Drag & drop files here...";
    // Just print kind of 'hay message'
    $scope.message = 'M. ' + ShamayimFunctions.getCookie("username");
    $scope.DebterName = "";
    $scope.house_id = "1";
    $scope.state;
    $scope.city;
    $scope.street;
    $scope.house_number;
    $scope.house_kind = 3;
    $scope.number_of_rooms = 1;
    $scope.number_of_living_rooms = 1;
    $scope.number_of_kitchens = 1;
    $scope.number_of_bedrooms = 3;
    $scope.number_of_bathrooms = 5;
    $scope.location_kind = 5;
    $scope.comments = "nice area have fun";
    $scope.purchase_price = 0.0;
    $scope.treatment_fees = 0.0;
    $scope.divers_fees = 0.0;
    $scope.renovation_fees_for_sale = 0.0;
    $scope.renovation_fees_for_renting = 0.0;


    $scope.foodName = "ext";
    $scope.price = "1.0";
    $scope.parve = false;
    $scope.vegan = false;
    $scope.halavi = false;
    $scope.inKeytring = false;
    $scope.vegetarian = false;
    $scope.inSucurSal = false;
    $scope.bassari = false;
    $scope.descryption = null;
    $scope.files;
    $scope.foodToUpload = {
        "foodName": "",
        "price": 0.0,
        "parve": false,
        "vegan": false,
        "halavi": false,
        "inKeytring": false,
        "vegetarian": false,
        "inSucurSal": false,
        "bassari": false,
        "descryption": ""
    }


    // House Profile Pictures Section
    $scope.$watch('files.length', function (newVal, oldVal) {
        console.log($scope.files);
    });

    // 12345
    $scope.updatePictures = function () {
        var formData = new FormData();
        for (var index = 0; index < $scope.files.length; index++) {
            console.log($scope.files[index].lfFile);
            formData.append('files[]', $scope.files[index].lfFile);
        }

        formData.append('foodToUpload',JSON.stringify($scope.foodToUpload));
        $http.post('/INSERT_FOOD', formData,{
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(function (result) {
            //alert(result);
        }, function (err) {
            //alert(err);
        });

    }

    // Get information concerning the
    $scope.insertFood = function () {

        var res = $http.post('/INSERT_FOOD', $scope.foodToUpload);
        res.success(function (data, status, headers, config) {
            alert(data);
        });
        res.error(function (data, status, headers, config) {
            alert("failure message: " + JSON.stringify({
                    data: data
                }));
        });


    }

    $scope.check = function (value) {
        if ((value == "bassari") && ($scope.foodToUpload.bassari == true)) {
            $scope.foodToUpload.parve = false;
            $scope.foodToUpload.halavi = false;
        }
        if ((value == "halavi") && ($scope.foodToUpload.halavi == true)) {
            $scope.foodToUpload.parve = false;
            $scope.foodToUpload.bassari = false;
        }
        if ((value == "parve") && ($scope.foodToUpload.parve == true)) {
            $scope.foodToUpload.bassari = false;
            $scope.foodToUpload.halavi = false;
        }
    }
    $scope.userName = ShamayimFunctions.getCookie("username");


    // Just check if there is a user name
    if (ShamayimFunctions.getCookie("username") == null) {
        // Go to the main application
        $state.go('wellcom');
    }


    $scope.items = [
        "bassari",
        "parve",
        "halavi",
        "vegan",
        "vegetarian",
        "inSucurSal",
        "inKeytring"]

    //  $scope.items = [1, 2, 3, 4, 5];
    $scope.selected = [];
    $scope.toggle = function (item, list) {
        var idx = list.indexOf(item);
        if (idx > -1) {
            list.splice(idx, 1);
        } else {
            list.push(item);
        }
    };
    $scope.exists = function (item, list) {
        return list.indexOf(item) > -1;
    };
    $scope.lala = function (trueOrFlaseValue) {
        var booleanToReturn;
        if (trueOrFlaseValue == "parve") {
            if ($scope.parve == false) {
                $scope.parve == true;
            }
        }
        return $scope.parve;
    }


}]);
