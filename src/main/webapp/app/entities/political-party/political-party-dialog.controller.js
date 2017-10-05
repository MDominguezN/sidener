(function() {
    'use strict';

    angular
        .module('sidenerApp')
        .controller('PoliticalPartyDialogController', PoliticalPartyDialogController);

    PoliticalPartyDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PoliticalParty'];

    function PoliticalPartyDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PoliticalParty) {
        var vm = this;

        vm.politicalParty = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        ini();

        function ini() {
            if(vm.politicalParty.id == null) {
                vm.politicalParty.published = true;
            }
        }

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.politicalParty.id !== null) {
                vm.politicalParty.updated = new Date();
                PoliticalParty.update(vm.politicalParty, onSaveSuccess, onSaveError);
            } else {
                vm.politicalParty.created = new Date();
                vm.politicalParty.updated = new Date();
                PoliticalParty.save(vm.politicalParty, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sidenerApp:politicalPartyUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.created = false;
        vm.datePickerOpenStatus.updated = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();