(function() {
    'use strict';
    angular
        .module('roadApp')
        .factory('Pagamento', Pagamento);

    Pagamento.$inject = ['$resource', 'DateUtils'];

    function Pagamento ($resource, DateUtils) {
        var resourceUrl =  'api/pagamentos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.data = DateUtils.convertLocalDateFromServer(data.data);
                        data.baixa = DateUtils.convertLocalDateFromServer(data.baixa);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.data = DateUtils.convertLocalDateToServer(copy.data);
                    copy.baixa = DateUtils.convertLocalDateToServer(copy.baixa);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.data = DateUtils.convertLocalDateToServer(copy.data);
                    copy.baixa = DateUtils.convertLocalDateToServer(copy.baixa);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
