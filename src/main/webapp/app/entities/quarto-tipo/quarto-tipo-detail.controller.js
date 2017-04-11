(function() {
    'use strict';

    angular
        .module('roadApp')
        .controller('QuartoTipoDetailController', QuartoTipoDetailController);

    QuartoTipoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'QuartoTipo'];

    function QuartoTipoDetailController($scope, $rootScope, $stateParams, previousState, entity, QuartoTipo) {
        var vm = this;

        vm.quartoTipo = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('roadApp:quartoTipoUpdate', function(event, result) {
            vm.quartoTipo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
