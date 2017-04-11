(function() {
    'use strict';

    angular
        .module('roadApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('quarto', {
            parent: 'entity',
            url: '/quarto?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'roadApp.quarto.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/quarto/quartos.html',
                    controller: 'QuartoController',
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
                    $translatePartialLoader.addPart('quarto');
                    $translatePartialLoader.addPart('quartoSexo');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('quarto-detail', {
            parent: 'quarto',
            url: '/quarto/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'roadApp.quarto.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/quarto/quarto-detail.html',
                    controller: 'QuartoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('quarto');
                    $translatePartialLoader.addPart('quartoSexo');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Quarto', function($stateParams, Quarto) {
                    return Quarto.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'quarto',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('quarto-detail.edit', {
            parent: 'quarto-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/quarto/quarto-dialog.html',
                    controller: 'QuartoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Quarto', function(Quarto) {
                            return Quarto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('quarto.new', {
            parent: 'quarto',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/quarto/quarto-dialog.html',
                    controller: 'QuartoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nome: null,
                                classe: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('quarto', null, { reload: 'quarto' });
                }, function() {
                    $state.go('quarto');
                });
            }]
        })
        .state('quarto.edit', {
            parent: 'quarto',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/quarto/quarto-dialog.html',
                    controller: 'QuartoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Quarto', function(Quarto) {
                            return Quarto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('quarto', null, { reload: 'quarto' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('quarto.delete', {
            parent: 'quarto',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/quarto/quarto-delete-dialog.html',
                    controller: 'QuartoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Quarto', function(Quarto) {
                            return Quarto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('quarto', null, { reload: 'quarto' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
