package com.app.service.http;

import com.app.exception.MyAppException;
import com.app.model.Actor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class HttpConnectionName {

    public HttpRequest requestGet(final String path) throws URISyntaxException {
        if (path == null) {
            throw new MyAppException(" wrong arg in HttpConnectionName/ requestGet method ");
        }
        return HttpRequest.newBuilder()
                .uri(new URI(path))
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.ofSeconds(10)) // HttpTimeoutException
                .GET()
                .header("x-rapidapi-host", "tvjan-tvmaze-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "3b4c49ee50mshf8541d04cee3998p114427jsna83153e0fb64")
                .build();
    }

    public String establishAsyncConnection(Long idNumber) {
        if (idNumber == null) {
            throw new MyAppException(" wrong arg in HttpConnectionName/ establishAsyncConnection method ");
        }

        String name;
        try {

            final String nbpPath = "https://tvjan-tvmaze-v1.p.rapidapi.com/people/" + idNumber;
            System.out.println("\n------------------LOADING--------------------");
            CompletableFuture<HttpResponse<String>> response1 = HttpClient
                    .newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .sendAsync(requestGet(nbpPath), HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Actor actor = gson.fromJson(response1.get().body(), Actor.class);
            name = actor.getName();
        } catch (Exception e) {
            throw new MyAppException("Error in HttpConnectionName establishConnection");
        }
        return name;
    }
}
