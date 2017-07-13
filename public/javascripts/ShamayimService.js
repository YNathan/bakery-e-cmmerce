var ShamayimService = angular.module('ShamayimService', []);

ShamayimService.factory('ShamayimFunctions', function ($http, $mdDialog) {
    var dictionary = {
        "Dictionary": [{
            "Dictionary": "English",
            "HouseId": "Number Of House That Recording In The System",
            "Address": "Address",
            "State": "State",
            "City": "City",
            "Street": "Street",
            "HouseNumber": "House Number",
            "HouseKind": "Kind Of House",
            "NumberOfRooms": "Number Of Rooms",
            "NumberOfLivingRooms": "Number Of Living Rooms",
            "NumberOfKitchens": "Number Of Kitchens",
            "NumberOfBedrooms": "Number Of Bedrooms",
            "NumberOfBathrooms": "Number Of Bathrooms",
            "LocationKind": "LocationKind",
            "Score": "Score",
            "Comments": "Comments",
            "PurchasePrice": "Purchase Price",
            "TreatmentFees": "Treatment Fees",
            "RenovationFeesForSale": "Renovation Fees For Sale",
            "RenovationFeesForRenting": "Renovation Fees For Renting",
            "GeneralHouseDetailes": "General House Details",
            "FinancialHouseDetailes": "Financial House Details",
            "DiversFees": "Divers Fees",
            "UploadHouseFiles": "Upload House Files",
            "DragOrDropFilesHere": "Drag & Drop Files Here",
            "HouseTable": "House Details Table",
            "Menu": "Menu",
            "Information": "Information",
            "House": "House",
            "ManageHouses": "Manage Houses",
            "NewHouse": "New House",
            "Copyright": "About",
            "WellcomToYourAccount": "Wellcom To Your Account",
            "SelectaHouse": "Select A House",
            "AreaOnTheMap": "Area On The Map",
            "HousesList": "Houses List",
            "Save": "Save"

        }]
    };

    function getDictionary() {
        return dictionary;
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

    // Get value from the cookie
    var getCookie = function (cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1);
            if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
        }
        return "";
    }

    // Language Section
    // For the Language
    var tempArr = [];
    var Languages = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            HouseLanguage: 'default'
        }
    };
    var getExistingLanguages = function () {
        $http.get('/GET_LIST_OF_EXISTING_LANGUAGES')
            .then(function successCallback(response) {
                    angular.forEach(response.data.languages, function (value, key) {
                        itemName = {
                            id: key,
                            HouseLanguage: value
                        }
                        tempArr.push(itemName);
                        Languages.availableOptions.push(itemName.HouseLanguage);
                    }, Languages);
                },
                function error(response) {
                    showAlert("Your attention please", response.data, "cant load houses");
                });
        return Languages;
    }
    var getLanguage = function (szLanguage) {
        // Get information conserning the house
        $http.get("/GET_LANGUAGE/" + szLanguage)
            .then(function successCallback(response) {
                    dictionary = response.data;
                    return true;
                },
                function error(response) {
                    showAlert("Your attention please", response.data, "cant load houses");
                    return false;
                });

    }
    var setIsLoggedCookie = function (szbIsLogged) {
        document.cookie = "Logged=" + szbIsLogged + "; ";
    }
    var setLanguageCookie = function (szLanguage) {
        document.cookie = "Language=" + szLanguage + "; ";
    }
    var setPermissionCookie = function (szPermission) {
            document.cookie = "Permission=" + szPermission + "; ";
        }
        // Get value from the cookie
    var getPermissionCookie = function () {
            var name = "Permission=";
            var ca = document.cookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) == ' ') c = c.substring(1);
                if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
            }
            return "";
        }
    // Get language from the cookie
    var getLanguageCookie = function () {
        var name = "Language=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1);
            if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
        }
        return "";
    }
    var getIsLoggedCookie = function () {
        var name = "Logged=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1);
            if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
        }
        return "";
    }

    function setUserNameCookie(szName, szValue) {
        document.cookie = szName + "=" + szValue + "; ";
    }
    return {
        getExistingLanguages: getExistingLanguages,
        getLanguage: getLanguage,
        showAlert: showAlert,
        getCookie: getCookie,
        dictionary: dictionary,
        getDictionary: getDictionary,
        setLanguageCookie: setLanguageCookie,
        setUserNameCookie: setUserNameCookie,
        setPermissionCookie: setPermissionCookie,
        getPermissionCookie: getPermissionCookie,
        setIsLoggedCookie : setIsLoggedCookie,
        getIsLoggedCookie : getIsLoggedCookie
    };
});
