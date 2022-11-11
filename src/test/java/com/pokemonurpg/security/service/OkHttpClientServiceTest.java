package com.pokemonurpg.security.service;

import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OkHttpClientServiceTest {
    private final static String RESPONSE_STRING = "GOOD_RESPONSE";
    private final static Call CALL = mock(Call.class);
    private final static ResponseBody RESPONSE_BODY = ResponseBody.create(
            MediaType.get("application/json; charset=utf-8"),
            RESPONSE_STRING
    );
    private final static Request REQUEST = new Request.Builder().url("http://google.com").build();
    private final static Response GOOD_RESPONSE = new Response.Builder().code(200).body(RESPONSE_BODY).request(REQUEST).protocol(Protocol.HTTP_2).message("MESSAGE").build();
    private final static Response BAD_RESPONSE = new Response.Builder().code(400).body(null).request(REQUEST).protocol(Protocol.HTTP_2).message("MESSAGE").build();

    private OkHttpClientService okHttpClientService = new OkHttpClientService();
    private OkHttpClient okHttpClient = mock(OkHttpClient.class);

    @BeforeEach
    public void init() {
        okHttpClientService.setHttpClient(okHttpClient);
    }

    @Test
    public void returnsStringResponseFromSuccessfulRequest() throws IOException {
        when(okHttpClient.newCall(REQUEST)).thenReturn(CALL);
        when(CALL.execute()).thenReturn(GOOD_RESPONSE);

        assertEquals(RESPONSE_STRING, okHttpClientService.sendRequest(REQUEST));
    }

    @Test
    public void throwsIOExceptionWhenResponseIsUnsuccessful() throws IOException {
        when(okHttpClient.newCall(REQUEST)).thenReturn(CALL);
        when(CALL.execute()).thenReturn(BAD_RESPONSE);

        assertThrows(IOException.class, () -> okHttpClientService.sendRequest(REQUEST));
    }

    @Test
    public void throwsIOExceptionWhenResponseBodyIsNull() throws IOException {
        when(okHttpClient.newCall(REQUEST)).thenReturn(CALL);
        when(CALL.execute()).thenReturn(BAD_RESPONSE);

        assertThrows(IOException.class, () -> okHttpClientService.sendRequest(REQUEST));
    }


}