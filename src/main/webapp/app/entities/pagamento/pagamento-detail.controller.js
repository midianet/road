(function() {
    'use strict';

    angular
        .module('roadApp')
        .controller('PagamentoDetailController', PagamentoDetailController);

    PagamentoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Pagamento', 'Pessoa'];

    function PagamentoDetailController($scope, $rootScope, $stateParams, previousState, entity, Pagamento, Pessoa) {
        var vm = this;

        vm.pagamento = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('roadApp:pagamentoUpdate', function(event, result) {
            vm.pagamento = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
