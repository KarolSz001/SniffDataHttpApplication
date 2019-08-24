package com.app.service.http.connect;

import java.net.URISyntaxException;
import java.net.http.HttpRequest;


public interface HttpConnection {

   HttpRequest requestGet(final String path) throws URISyntaxException;

}
