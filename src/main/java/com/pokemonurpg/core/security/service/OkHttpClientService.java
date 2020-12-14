package com.pokemonurpg.core.security.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OkHttpClientService {

    private OkHttpClient httpClient = new OkHttpClient();

    public String sendRequest(Request request) throws IOException {
        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            }
            throw new IOException();
        } catch (Exception e) {
            throw new IOException();
        }
    }

    public void setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
