(function() {
    'use strict';

    angular
        .module('roadApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('quarto-tipo', {
            parent: 'entity',
            url: '/quarto-tipo?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'roadApp.quartoTipo.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/quarto-tipo/quarto-tipos.html',
                    controller: 'QuartoTipoController',
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
                    $translatePartialLoader.addPart('quartoTipo');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('quarto-tipo-detail', {
            parent: 'quarto-tipo',
            url: '/quarto-tipo/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'roadApp.quartoTipo.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/quarto-tipo/quarto-tipo-detail.html',
                    controller: 'QuartoTipoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('quartoTipo');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'QuartoTipo', function($stateParams, QuartoTipo) {
                    return QuartoTipo.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'quarto-tipo',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('quarto-tipo-detail.edit', {
            parent: 'quarto-tipo-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/quarto-tipo/quarto-tipo-dialog.html',
                    controller: 'QuartoTipoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['QuartoTipo', function(QuartoTipo) {
                            return QuartoTipo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('quarto-tipo.new', {
            parent: 'quarto-tipo',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/quarto-tipo/quarto-tipo-dialog.html',
                    controller: 'QuartoTipoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                descricao: null,
                                valor: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('quarto-tipo', null, { reload: 'quarto-tipo' });
                }, function() {
                    $state.go('quarto-tipo');
                });
            }]
        })
        .state('quarto-tipo.edit', {
            parent: 'quarto-tipo',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/quarto-tipo/quarto-tipo-dialog.html',
                    controller: 'QuartoTipoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['QuartoTipo', function(QuartoTipo) {
                            return QuartoTipo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('quarto-tipo', null, { reload: 'quarto-tipo' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('quarto-tipo.delete', {
            parent: 'quarto-tipo',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/quarto-tipo/quarto-tipo-delete-dialog.html',
                    controller: 'QuartoTipoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['QuartoTipo', function(QuartoTipo) {
                            return QuartoTipo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('quarto-tipo', null, { reload: 'quarto-tipo' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
