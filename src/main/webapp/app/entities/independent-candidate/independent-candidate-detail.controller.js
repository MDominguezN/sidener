(function() {
    'use strict';

    angular
        .module('sidenerApp')
        .controller('IndependentCandidateDetailController', IndependentCandidateDetailController);

    IndependentCandidateDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'IndependentCandidate'];

    function IndependentCandidateDetailController($scope, $rootScope, $stateParams, previousState, entity, IndependentCandidate) {
        var vm = this;

        vm.independentCandidate = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sidenerApp:independentCandidateUpdate', function(event, result) {
            vm.independentCandidate = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();