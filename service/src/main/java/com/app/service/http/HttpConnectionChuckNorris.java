package com.app.service.http;

import com.app.exception.MyAppException;
import com.app.model.Joke;
import com.app.service.http.connect.HttpConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;


public class HttpConnectionChuckNorris implements HttpConnection {

    @Override
    public HttpRequest requestGet(final String path) throws URISyntaxException {

        return HttpRequest.newBuilder()
                .uri(new URI(path))
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.ofSeconds(10)) // HttpTimeoutException
                .GET()
                .header("x-rapidapi-host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "3b4c49ee50mshf8541d04cee3998p114427jsna83153e0fb64")
                .header("accept", "application/json")
                .build();
    }


    public Integer establishAsyncConnection() {

        String[] wordsInPhrase;
        Integer factor = 100;

        try {

            final String nbpPath = "https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random";
            System.out.println("\n------------------ LOADING --------------------");
            CompletableFuture<HttpResponse<String>> response1 = HttpClient
                    .newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .sendAsync(requestGet(nbpPath), HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Joke joke = gson.fromJson(response1.get().body(), Joke.class);
            System.out.println(joke.getValue());
            wordsInPhrase = joke.getValue().split(" ");

        } catch (Exception e) {
            throw new MyAppException("Error in HttpConnectionChuckNorris establishConnection");
        }

        return Objects.requireNonNull(wordsInPhrase).length * factor;
    }


}
