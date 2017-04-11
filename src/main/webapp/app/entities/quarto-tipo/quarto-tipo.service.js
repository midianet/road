(function() {
    'use strict';
    angular
        .module('roadApp')
        .factory('QuartoTipo', QuartoTipo);

    QuartoTipo.$inject = ['$resource'];

    function QuartoTipo ($resource) {
        var resourceUrl =  'api/quarto-tipos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
