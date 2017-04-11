(function() {
    'use strict';

    angular
        .module('roadApp')
        .controller('QuartoTipoDialogController', QuartoTipoDialogController);

    QuartoTipoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'QuartoTipo'];

    function QuartoTipoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, QuartoTipo) {
        var vm = this;

        vm.quartoTipo = entity;
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
            if (vm.quartoTipo.id !== null) {
                QuartoTipo.update(vm.quartoTipo, onSaveSuccess, onSaveError);
            } else {
                QuartoTipo.save(vm.quartoTipo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('roadApp:quartoTipoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
