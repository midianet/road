(function() {
    'use strict';

    angular
        .module('roadApp')
        .controller('QuartoTipoDeleteController',QuartoTipoDeleteController);

    QuartoTipoDeleteController.$inject = ['$uibModalInstance', 'entity', 'QuartoTipo'];

    function QuartoTipoDeleteController($uibModalInstance, entity, QuartoTipo) {
        var vm = this;

        vm.quartoTipo = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            QuartoTipo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
