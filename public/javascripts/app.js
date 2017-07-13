/**
 * Created by Yaacov on 29/12/2015.
 */
app = angular.module('root', ['ui.router', 'ngMaterial', 'ngMessages', 'lfNgMdFileInput', 'ShamayimService','cl.paging']);
app.config(function ($stateProvider, $urlRouterProvider, $mdIconProvider, $mdToastProvider) {
    $mdIconProvider.defaultIconSet('./svg/avatars.svg', 128);
    $mdIconProvider.icon('share', './svg/share.svg', 24);
    $mdIconProvider.icon('menu', './svg/menu.svg', 24);
    $mdIconProvider.icon('account_circle', './svg/account_circle.svg');
    $mdIconProvider.icon('attach_money', './svg/attach_money.svg');
    $mdIconProvider.icon('copyright', './svg/copyright.svg');
    $mdIconProvider.icon('home', './svg/home.svg');
    $mdIconProvider.icon('logout', './svg/assignment_return.svg');
    $mdIconProvider.icon('login', './svg/login.svg');


    //
    // For any unmatched url, redirect to /state1
    $urlRouterProvider.otherwise("/welcome");
    //
    // Now set up the states
    $stateProvider
        .state('welcome', {
            url: "/welcome",
            templateUrl: "template/Welcome.html",
            controller: 'welcome'
        })
        .state('Manager', {
            url: "/manager",
            templateUrl: "template/Manager.html",
            controller: 'manager'
        })
        .state('Houses', {
            url: "/houses",
            templateUrl: "template/Houses.html",
            controller: 'houses'
        })
        .state('House', {
            url: "/house",
            templateUrl: "template/House.html",
            controller: 'house'
        })
        .state('NewOrEditHouse', {
            url: "/neworedithouse",
            templateUrl: "template/NewOrEditHouse.html",
            controller: 'neworedithouse'
        })
        .state('Copyright', {
            url: "/Copyright",
            templateUrl: "template/Copyright.html",
            controller: 'Copyright'
        });
});
