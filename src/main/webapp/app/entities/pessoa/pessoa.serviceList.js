(function() {
    'use strict';
    angular
        .module('roadApp')
        .factory('PessoaList', PessoaList);

    PessoaList.$inject = ['$resource', 'DateUtils'];

    function PessoaList ($resource, DateUtils) {
        var resourceUrl =  'api/pessoas/list';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.registro = DateUtils.convertLocalDateFromServer(data.registro);
                    }
                    return data;
                }
            }
        });
    }
})();
