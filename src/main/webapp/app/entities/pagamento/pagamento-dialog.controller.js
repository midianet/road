(function() {
    'use strict';

    angular
        .module('roadApp')
        .controller('PagamentoDialogController', PagamentoDialogController);

    PagamentoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Pagamento', 'Pessoa'];

    function PagamentoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Pagamento, Pessoa) {
        var vm = this;

        vm.pagamento = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.pessoas = Pessoa.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.pagamento.id !== null) {
                Pagamento.update(vm.pagamento, onSaveSuccess, onSaveError);
            } else {
                Pagamento.save(vm.pagamento, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('roadApp:pagamentoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.data = false;
        vm.datePickerOpenStatus.baixa = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
