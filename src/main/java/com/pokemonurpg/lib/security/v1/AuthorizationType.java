package com.pokemonurpg.lib.security.v1;

public enum AuthorizationType {
    ALLOW_ALL {
        @Override
        Class<? extends AuthorizationService> getAuthorizationServiceClass() {
            return AllowAllAuthorizationService.class;
        }
    },
    ALLOW_OWNER {
        @Override
        Class<? extends AuthorizationService> getAuthorizationServiceClass() {
            return AllowOwnerAuthorizationService.class;
        }
    },
    ALLOW_AUTHENTICATED {
        @Override
        Class<? extends AuthorizationService> getAuthorizationServiceClass() {
            return AllowAuthenticatedAuthorizationService.class;
        }
    },
    ALLOW_AUTHORIZED {
        @Override
        Class<? extends AuthorizationService> getAuthorizationServiceClass() {
            return AllowAuthorizedAuthorizationService.class;
        }
    },
    DENY_ALL {
        @Override
        Class<? extends AuthorizationService> getAuthorizationServiceClass() {
            return DenyAllAuthorizationService.class;
        }
    };

    abstract Class<? extends AuthorizationService> getAuthorizationServiceClass(); 
}
