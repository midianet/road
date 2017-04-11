(function() {
    'use strict';
    angular
        .module('roadApp')
        .factory('Quarto', Quarto);

    Quarto.$inject = ['$resource'];

    function Quarto ($resource) {
        var resourceUrl =  'api/quartos/:id';

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
