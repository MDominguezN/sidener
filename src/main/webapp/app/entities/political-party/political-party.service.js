(function() {
    'use strict';
    angular
        .module('sidenerApp')
        .factory('PoliticalParty', PoliticalParty);

    PoliticalParty.$inject = ['$resource', 'DateUtils'];

    function PoliticalParty ($resource, DateUtils) {
        var resourceUrl =  'api/political-parties/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createdDate = DateUtils.convertDateTimeFromServer(data.createdDate);
                        data.updatedDate = DateUtils.convertDateTimeFromServer(data.updatedDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
