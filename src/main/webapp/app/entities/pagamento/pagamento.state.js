(function() {
    'use strict';

    angular
        .module('roadApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('pagamento', {
            parent: 'entity',
            url: '/pagamento?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'roadApp.pagamento.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pagamento/pagamentos.html',
                    controller: 'PagamentoController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('pagamento');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('pagamento-detail', {
            parent: 'pagamento',
            url: '/pagamento/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'roadApp.pagamento.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pagamento/pagamento-detail.html',
                    controller: 'PagamentoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('pagamento');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Pagamento', function($stateParams, Pagamento) {
                    return Pagamento.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'pagamento',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('pagamento-detail.edit', {
            parent: 'pagamento-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pagamento/pagamento-dialog.html',
                    controller: 'PagamentoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Pagamento', function(Pagamento) {
                            return Pagamento.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pagamento.new', {
            parent: 'pagamento',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pagamento/pagamento-dialog.html',
                    controller: 'PagamentoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                data: null,
                                baixa: null,
                                valor: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('pagamento', null, { reload: 'pagamento' });
                }, function() {
                    $state.go('pagamento');
                });
            }]
        })
        .state('pagamento.edit', {
            parent: 'pagamento',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pagamento/pagamento-dialog.html',
                    controller: 'PagamentoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Pagamento', function(Pagamento) {
                            return Pagamento.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pagamento', null, { reload: 'pagamento' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pagamento.delete', {
            parent: 'pagamento',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pagamento/pagamento-delete-dialog.html',
                    controller: 'PagamentoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Pagamento', function(Pagamento) {
                            return Pagamento.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pagamento', null, { reload: 'pagamento' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
