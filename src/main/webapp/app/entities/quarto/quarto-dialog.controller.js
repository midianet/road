(function() {
    'use strict';

    angular
        .module('roadApp')
        .controller('QuartoDialogController', QuartoDialogController);

    QuartoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Quarto', 'QuartoTipo'];

    function QuartoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Quarto, QuartoTipo) {
        var vm = this;

        vm.quarto = entity;
        vm.clear = clear;
        vm.save = save;
        vm.quartotipos = QuartoTipo.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.quarto.id !== null) {
                Quarto.update(vm.quarto, onSaveSuccess, onSaveError);
            } else {
                Quarto.save(vm.quarto, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('roadApp:quartoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
