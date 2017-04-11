(function() {
    'use strict';

    angular
        .module('roadApp')
        .controller('ParametroDetailController', ParametroDetailController);

    ParametroDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Parametro'];

    function ParametroDetailController($scope, $rootScope, $stateParams, previousState, entity, Parametro) {
        var vm = this;

        vm.parametro = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('roadApp:parametroUpdate', function(event, result) {
            vm.parametro = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
