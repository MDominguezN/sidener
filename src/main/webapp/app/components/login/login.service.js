(function() {
    'use strict';

    angular
        .module('sidenerApp')
        .factory('LoginService', LoginService);

    LoginService.$inject = ['$state', '$uibModal'];

    function LoginService ($state, $uibModal) {
        var service = {
            open: open,
            go: go
        };

        var modalInstance = null;
        var resetModal = function () {
            modalInstance = null;
        };

        return service;

        function open () {
            if (modalInstance !== null) return;
            modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/components/login/login.html',
                controller: 'LoginController',
                controllerAs: 'vm',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('login');
                        return $translate.refresh();
                    }]
                }
            });
            modalInstance.result.then(
                resetModal,
                resetModal
            );
        }

        function go () {
            $state.go('login');
        }
    }
})();
