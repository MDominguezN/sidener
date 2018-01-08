(function() {
    'use strict';

    angular
        .module('sidenerApp')
        .controller('IndependentCandidateDialogController', IndependentCandidateDialogController);

    IndependentCandidateDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'IndependentCandidate', 'Archive'];

    function IndependentCandidateDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, IndependentCandidate, Archive) {
        var vm = this;

        vm.independentCandidate = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        vm.images = Archive.query({filter: 'independentcandidate-is-null'});

        $q.all([vm.independentCandidate.$promise, vm.images.$promise]).then(function() {
            if (!vm.independentCandidate.imageId) {
                return $q.reject();
            }
            return Archive.get({id : vm.independentCandidate.imageId}).$promise;
        }).then(function(image) {
            vm.images.push(image);
        });

        ini();

        function ini() {
            if(vm.independentCandidate.id == null) {
                vm.independentCandidate.published = true;
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
            if (vm.independentCandidate.id !== null) {
                vm.independentCandidate.updatedDate = new Date();
                IndependentCandidate.update(vm.independentCandidate, onSaveSuccess, onSaveError);
            } else {
                vm.independentCandidate.createdDate = new Date();
                vm.independentCandidate.updatedDate = new Date();
                IndependentCandidate.save(vm.independentCandidate, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sidenerApp:independentCandidateUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.createdDate = false;
        vm.datePickerOpenStatus.updatedDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
