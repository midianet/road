(function() {
    'use strict';

    angular
        .module('roadApp')
        .controller('QuartoDeleteController',QuartoDeleteController);

    QuartoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Quarto'];

    function QuartoDeleteController($uibModalInstance, entity, Quarto) {
        var vm = this;

        vm.quarto = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Quarto.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
