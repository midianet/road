(function() {
    'use strict';

    angular
        .module('roadApp')
        .controller('QuartoDetailController', QuartoDetailController);

    QuartoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Quarto', 'QuartoTipo'];

    function QuartoDetailController($scope, $rootScope, $stateParams, previousState, entity, Quarto, QuartoTipo) {
        var vm = this;

        vm.quarto = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('roadApp:quartoUpdate', function(event, result) {
            vm.quarto = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
