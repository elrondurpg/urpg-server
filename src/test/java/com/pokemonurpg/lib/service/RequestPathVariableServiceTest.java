package com.pokemonurpg.lib.service;

import com.pokemonurpg.lib.v1.service.RequestPathVariableService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.*;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestPathVariableServiceTest {
    private final static Integer DBID = 4322;
    private final static String NAME = "NAME";
    private final static String INT_AS_STRING = "89742";

    private RequestPathVariableService requestPathVariableService = new RequestPathVariableService();

    private HttpServletRequest request = mock(HttpServletRequest.class);

    @Before
    public void init() {
        Map<String, Object> pathVariables = new HashMap<>();
        pathVariables.put("dbid", DBID);
        pathVariables.put("name", NAME);
        pathVariables.put("intAsString", INT_AS_STRING);

        when(request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE)).thenReturn(pathVariables);
    }

    @Test
    public void findStringByName() {
        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);
        assertEquals(NAME, requestPathVariableService.findStringByName("name"));
    }

    @Test
    public void findStringByNameReturnsNullWhenVariableDoesntExist() {
        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);
        assertNull(requestPathVariableService.findStringByName("invalid"));
    }

    @Test
    public void findStringByNameReturnsNullWhenAttrsIsNull() {
        RequestContextHolder.setRequestAttributes(null);
        assertNull(requestPathVariableService.findStringByName("name"));
    }

    @Test
    public void findStringByName_ReturnsNull_OnException() {
        request = mock(HttpServletRequest.class);
        when(request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE)).thenThrow(new IllegalStateException());
        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);
        assertNull(requestPathVariableService.findStringByName("name"));
    }

    @Test
    public void findIntegerByName() {
        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);
        assertEquals(DBID, requestPathVariableService.findIntByName("dbid"));
    }

    @Test
    public void findIntegerByNameReturnsNullWhenVariableDoesntExist() {
        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);
        assertNull(requestPathVariableService.findIntByName("invalid"));
    }

    @Test
    public void findIntegerByNameReturnsNullWhenAttrsIsNull() {
        RequestContextHolder.setRequestAttributes(null);
        assertNull(requestPathVariableService.findIntByName("dbid"));
    }

    @Test
    public void findIntegerByName_ReturnsNull_OnException() {
        request = mock(HttpServletRequest.class);
        when(request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE)).thenThrow(new IllegalStateException());
        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);
        assertNull(requestPathVariableService.findIntByName("dbid"));
    }

    @Test
    public void findIntegerByName_ReturnsInteger_WhenVariableIsAString() {
        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);
        assertEquals((Integer) Integer.parseInt(INT_AS_STRING), requestPathVariableService.findIntByName("intAsString"));
    }
}