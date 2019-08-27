package com.app.service.http;

import com.app.exception.MyAppException;
import com.app.service.http.connect.HttpConnection;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class HttpConnectionCalc implements HttpConnection {

    @Override
    public HttpRequest requestGet(final String path) throws URISyntaxException {

        if (path == null) {
            throw new MyAppException(" wrong arg in requestGet method ");
        }
        return HttpRequest.newBuilder()
                .uri(new URI(path))
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.ofSeconds(10)) // HttpTimeoutException
                .GET()
                .headers("x-rapidapi-host", "currency-exchange.p.rapidapi.com")
                .headers("x-rapidapi-key", "3b4c49ee50mshf8541d04cee3998p114427jsna83153e0fb64")
                .build();
    }

    public Double establishAsyncConnection(String currency) {

        if (currency == null) {
            throw new MyAppException(" wrong arg in establishAsyncConnection method ");
        }
        BigDecimal bd;
        try {
            final String nbpPath = "https://currency-exchange.p.rapidapi.com/exchange?q=1.0&from=USD&to=" + currency;
            System.out.println("------------------LOADING--------------------");

            CompletableFuture<HttpResponse<String>> response = HttpClient
                    .newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .sendAsync(requestGet(nbpPath), HttpResponse.BodyHandlers.ofString());


            bd = BigDecimal.valueOf(Double.parseDouble(response.get().body()));

        } catch (Exception e) {
            throw new MyAppException("Error in HttpConnectionCalc establishConnection");
        }
        return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
