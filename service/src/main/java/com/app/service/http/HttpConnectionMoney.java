package com.app.service.http;

import com.app.exception.MyAppException;
import com.app.service.http.connect.HttpConnection;

import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class HttpConnectionMoney implements HttpConnection {

    @Override
    public HttpRequest requestGet(String path) throws URISyntaxException {
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
    /**
     * Methods establish connection with free API. Get definition of currency which are available in database
     * @arg none
     * @return get List<String> with currency shortcuts
     */

    public List<String> establishAsyncConnection() {
        List<String> solution;

        try {
            final String nbpPath = "https://currency-exchange.p.rapidapi.com/listquotes";
            System.out.println("\n------------------LOADING--------------------");
            CompletableFuture<HttpResponse<String>> response = HttpClient
                    .newBuilder()
                    .proxy(ProxySelector.getDefault())
                    .build()
                    .sendAsync(requestGet(nbpPath), HttpResponse.BodyHandlers.ofString());

            solution = Arrays.asList(response.get().body().split(","));

        } catch (Exception e) {
            throw new MyAppException("Error in HttpConnectionMoney establishConnection");
        }
        return solution;
    }


}
