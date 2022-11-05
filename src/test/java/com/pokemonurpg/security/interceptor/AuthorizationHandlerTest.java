package com.pokemonurpg.security.interceptor;

import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.core.service.RequestPathVariableService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.security.annotation.*;
import com.pokemonurpg.security.service.AuthorizationService;
import com.pokemonurpg.security.service.SessionService;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import com.pokemonurpg.test.HttpServletRequestImpl;
import com.pokemonurpg.test.HttpServletResponseImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorizationHandlerTest {
    private static final Integer        DBID            = 4322;
    private static final Integer        OTHER_DBID      = 983472;
    private static final Member         MEMBER          = mock(Member.class);
    private static final Member         OTHER_MEMBER    = mock(Member.class);
    private static final OwnedPokemon   POKEMON         = mock(OwnedPokemon.class);
    private static final SessionService SESSION_SERVICE = mock(SessionService.class);
    private static final String         PERMISSION      = "PERMISSION";

    @InjectMocks
    private AuthorizationHandler authorizationHandler;

    @Mock
    private Provider<SessionService> sessionServiceProvider;

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private RequestPathVariableService requestPathVariableService;

    @Mock
    private OwnedPokemonService ownedPokemonService;

    class TestClass {
        @AllowAll
        public void allowAllMethod() {}

        @AllowAuthenticated
        public void authenticatedMethod() {}

        @AllowAuthorized(permission = PERMISSION)
        public void authorizedMethod() {}

        @AllowThisMember
        public void allowThisMemberMethod() {

        }

        @AllowTheOwner(type = Type.class)
        public void allowTheUnsupportedOwnerMethod() {}

        @AllowTheOwner(type = OwnedPokemon.class)
        public void allowThePokemonOwnerMethod() {}

        public void unannotatedMethod() {}
    }

    private HandlerMethod allowAllHandlerMethod = null;
    private HandlerMethod authorizedHandlerMethod = null;
    private HandlerMethod unannotatedHandlerMethod = null;
    private HandlerMethod authenticatedHandlerMethod = null;
    private HandlerMethod allowThisMemberHandlerMethod = null;
    private HandlerMethod allowThePokemonOwnerHandlerMethod = null;
    private HandlerMethod allowTheUnsupportedOwnerHandlerMethod = null;

    private HttpServletRequest request = new HttpServletRequestImpl();
    private HttpServletResponse response = new HttpServletResponseImpl();

    @Before
    public void init() throws NoSuchMethodException {
        when(sessionServiceProvider.get()).thenReturn(SESSION_SERVICE);
        allowAllHandlerMethod = new HandlerMethod(new TestClass(), TestClass.class.getMethod("allowAllMethod"));
        authorizedHandlerMethod = new HandlerMethod(new TestClass(), TestClass.class.getMethod("authorizedMethod"));
        unannotatedHandlerMethod = new HandlerMethod(new TestClass(), TestClass.class.getMethod("unannotatedMethod"));
        authenticatedHandlerMethod = new HandlerMethod(new TestClass(), TestClass.class.getMethod("authenticatedMethod"));
        allowThisMemberHandlerMethod = new HandlerMethod(new TestClass(), TestClass.class.getMethod("allowThisMemberMethod"));
        allowThePokemonOwnerHandlerMethod = new HandlerMethod(new TestClass(), TestClass.class.getMethod("allowThePokemonOwnerMethod"));
        allowTheUnsupportedOwnerHandlerMethod = new HandlerMethod(new TestClass(), TestClass.class.getMethod("allowTheUnsupportedOwnerMethod"));
    }

    @Test
    public void preHandle_ReturnsTrue_WhenSessionIsAuthorized() {
        when(authorizationService.isAuthorized(PERMISSION)).thenReturn(true);
        assertTrue(authorizationHandler.preHandle(request, response, authorizedHandlerMethod));
    }

    @Test
    public void preHandle_ReturnsTrue_WhenMethodIsAllowAll() {
        assertTrue(authorizationHandler.preHandle(request, response, allowAllHandlerMethod));
    }

    @Test
    public void preHandle_ReturnsFalse_WhenSessionIsUnauthorized() {
        when(authorizationService.isAuthorized(PERMISSION)).thenReturn(false);
        assertFalse(authorizationHandler.preHandle(request, response, authorizedHandlerMethod));
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
    }

    @Test
    public void preHandle_ReturnsFalse_WhenMethodHasNoAnnotation() {
        assertFalse(authorizationHandler.preHandle(request, response, unannotatedHandlerMethod));
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
    }

    @Test
    public void preHandle_ReturnsFalse_WhenHandlerIsNull() {
        assertFalse(authorizationHandler.preHandle(request, response, null));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    }

    @Test
    public void preHandle_ReturnsFalse_OnException() {
        when(authorizationService.isAuthorized(PERMISSION)).thenThrow(new IllegalStateException());
        assertFalse(authorizationHandler.preHandle(request, response, authorizedHandlerMethod));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    }

    @Test
    public void allowAuthenticated_ReturnsFalse_WhenAnnotationIsNull() {
        assertFalse(authorizationHandler.allowAuthenticated(unannotatedHandlerMethod));
    }

    @Test
    public void allowAuthenticated_ReturnsFalse_WhenRequestorIsUnauthenticated() {
        when(sessionServiceProvider.get()).thenReturn(SESSION_SERVICE);
        when(SESSION_SERVICE.getAuthenticatedMember()).thenReturn(null);
        assertFalse(authorizationHandler.allowAuthenticated(authenticatedHandlerMethod));
    }

    @Test
    public void allowAuthenticated_ReturnsTrue_WhenRequestorIsAuthenticated() {
        when(sessionServiceProvider.get()).thenReturn(SESSION_SERVICE);
        when(SESSION_SERVICE.getAuthenticatedMember()).thenReturn(MEMBER);
        assertTrue(authorizationHandler.allowAuthenticated(authenticatedHandlerMethod));
    }

    @Test
    public void allowThisMember_ReturnsFalse_WhenAnnotationIsNull() {
        assertFalse(authorizationHandler.allowThisMember(unannotatedHandlerMethod));
    }

    @Test
    public void allowThisMember_ReturnsFalse_WhenRequestDbidIsNull() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(null);
        assertFalse(authorizationHandler.allowThisMember(allowThisMemberHandlerMethod));
    }

    @Test
    public void allowThisMember_ReturnsFalse_WhenRequestorIsNotAuthenticated() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        assertFalse(authorizationHandler.allowThisMember(allowThisMemberHandlerMethod));
        when(sessionServiceProvider.get()).thenReturn(SESSION_SERVICE);
        when(SESSION_SERVICE.getAuthenticatedMember()).thenReturn(null);
        assertFalse(authorizationHandler.allowThisMember(allowThisMemberHandlerMethod));
    }

    @Test
    public void allowThisMember_ReturnsFalse_WhenRequestDbidDoesNotMatchAuthenticatedMemberDbid() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(sessionServiceProvider.get()).thenReturn(SESSION_SERVICE);
        when(SESSION_SERVICE.getAuthenticatedMember()).thenReturn(MEMBER);
        when(MEMBER.getDbid()).thenReturn(OTHER_DBID);
        assertFalse(authorizationHandler.allowThisMember(allowThisMemberHandlerMethod));
    }

    @Test
    public void allowThisMember_ReturnsTrue_WhenRequestDbidMatchesAuthenticatedMemberDbid() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(sessionServiceProvider.get()).thenReturn(SESSION_SERVICE);
        when(SESSION_SERVICE.getAuthenticatedMember()).thenReturn(MEMBER);
        when(MEMBER.getDbid()).thenReturn(DBID);
        assertTrue(authorizationHandler.allowThisMember(allowThisMemberHandlerMethod));
    }

    @Test
    public void allowTheOwner_ReturnsFalse_WhenAnnotationIsNull() {
        assertFalse(authorizationHandler.allowTheOwner(unannotatedHandlerMethod));
    }

    @Test
    public void allowTheOwner_ReturnsFalse_WhenAnnotationTypeIsUnsupported() {
        assertFalse(authorizationHandler.allowTheOwner(allowTheUnsupportedOwnerHandlerMethod));
    }

    @Test
    public void allowThePokemonOwner_ReturnsFalse_WhenPokemonIsNull() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(ownedPokemonService.findByDbid(DBID)).thenReturn(null);
        assertFalse(authorizationHandler.allowTheOwner(allowThePokemonOwnerHandlerMethod));
    }

    @Test
    public void allowThePokemonOwner_ReturnsFalse_WhenPokemonHasNoTrainer() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(ownedPokemonService.findByDbid(DBID)).thenReturn(POKEMON);
        when(POKEMON.getTrainer()).thenReturn(null);
        assertFalse(authorizationHandler.allowTheOwner(allowThePokemonOwnerHandlerMethod));
    }

    @Test
    public void allowThePokemonOwner_ReturnsFalse_WhenRequestorIsNotAuthenticated() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(ownedPokemonService.findByDbid(DBID)).thenReturn(POKEMON);
        when(POKEMON.getTrainer()).thenReturn(MEMBER);
        when(sessionServiceProvider.get()).thenReturn(SESSION_SERVICE);
        when(SESSION_SERVICE.getAuthenticatedMember()).thenReturn(null);
        assertFalse(authorizationHandler.allowTheOwner(allowThePokemonOwnerHandlerMethod));
    }

    @Test
    public void allowThePokemonOwner_ReturnsFalse_WhenPokemonOwnerDoesNotMatchRequestor() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(ownedPokemonService.findByDbid(DBID)).thenReturn(POKEMON);
        when(POKEMON.getTrainer()).thenReturn(MEMBER);
        when(sessionServiceProvider.get()).thenReturn(SESSION_SERVICE);
        when(SESSION_SERVICE.getAuthenticatedMember()).thenReturn(OTHER_MEMBER);
        when(MEMBER.getDbid()).thenReturn(DBID);
        when(OTHER_MEMBER.getDbid()).thenReturn(OTHER_DBID);
        assertFalse(authorizationHandler.allowTheOwner(allowThePokemonOwnerHandlerMethod));
    }

    @Test
    public void allowThePokemonOwner_ReturnsTrue_WhenPokemonOwnerMatchesRequestor() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(ownedPokemonService.findByDbid(DBID)).thenReturn(POKEMON);
        when(POKEMON.getTrainer()).thenReturn(MEMBER);
        when(sessionServiceProvider.get()).thenReturn(SESSION_SERVICE);
        when(SESSION_SERVICE.getAuthenticatedMember()).thenReturn(MEMBER);
        when(MEMBER.getDbid()).thenReturn(DBID);
        assertTrue(authorizationHandler.allowTheOwner(allowThePokemonOwnerHandlerMethod));
    }

}