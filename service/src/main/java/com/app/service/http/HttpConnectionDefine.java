package com.app.service.http;

import com.app.exception.MyAppException;
import com.app.model.Definition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


public class HttpConnectionDefine {

    public HttpRequest requestGet(final String path) throws URISyntaxException {
        if (path == null){
            throw new MyAppException(" wrong arg in requestGet method ");
        }
        return HttpRequest.newBuilder()
                .uri(new URI(path))
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.ofSeconds(10)) // HttpTimeoutException
                .GET()
                .header("x-rapidapi-host", "mashape-community-urban-dictionary.p.rapidapi.com")
                .header("x-rapidapi-key", "3b4c49ee50mshf8541d04cee3998p114427jsna83153e0fb64")
                .build();
    }

    public Definition establishSyncConnection(String text) {
        if (text== null){
            throw new MyAppException(" wrong arg in HttpConnectionDefine / establishSyncConnection method ");
        }
        Definition definition;
        try {
            final String nbpPath = "https://mashape-community-urban-dictionary.p.rapidapi.com/define?term=" + text;
            System.out.println("\n------------------LOADING--------------------");
            HttpResponse<String> response1 = HttpClient
                    .newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .send(requestGet(nbpPath), HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            definition = gson.fromJson(response1.body(), Definition.class);


        } catch (Exception e) {
            throw new MyAppException("Error in HttpConnectionDefine establishConnection");
        }
        return definition;
    }


}
