(function() {
    'use strict';
    angular
        .module('roadApp')
        .factory('Pessoa', Pessoa);

    Pessoa.$inject = ['$resource', 'DateUtils'];

    function Pessoa ($resource, DateUtils) {
        var resourceUrl =  'api/pessoas/:id';

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
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.registro = DateUtils.convertLocalDateToServer(copy.registro);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.registro = DateUtils.convertLocalDateToServer(copy.registro);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
