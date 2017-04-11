(function() {
    'use strict';

    angular
        .module('roadApp')
        .controller('ParametroDialogController', ParametroDialogController);

    ParametroDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Parametro'];

    function ParametroDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Parametro) {
        var vm = this;

        vm.parametro = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.parametro.id !== null) {
                Parametro.update(vm.parametro, onSaveSuccess, onSaveError);
            } else {
                Parametro.save(vm.parametro, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('roadApp:parametroUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
