(function() {
    'use strict';

    angular
        .module('sidenerApp')
        .controller('CausalDialogController', CausalDialogController);

    CausalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Causal'];

    function CausalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Causal) {
        var vm = this;

        vm.causal = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.causal.id !== null) {
                Causal.update(vm.causal, onSaveSuccess, onSaveError);
            } else {
                Causal.save(vm.causal, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sidenerApp:causalUpdate', result);
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
