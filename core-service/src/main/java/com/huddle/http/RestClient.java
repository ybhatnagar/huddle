package com.huddle.http;

import java.net.URI;
import java.util.Map;
import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class RestClient {

    private RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void postConstruct() {
        HttpClient client = HttpClients.createDefault();
        // RestTemplate by default doesn't support PATCH request, so need to add following code.
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
    }

    public <T> ResponseEntity<T> request(String serviceHostUri, String path, HttpHeaders headers, Class<T> responseType,
                                         HttpMethod httpMethod, Object body) {
        URI uri = UriUtils.buildUri(serviceHostUri, path);
        return getResponse(uri, responseType, httpMethod, body, headers);
    }

    public <T> ResponseEntity<T> request(String serviceHostUri, String path, HttpHeaders headers, Class<T> responseType,
                                         HttpMethod httpMethod, Object body, Map<String, ?> uriVariables) {
        URI uri = UriUtils.buildUri(serviceHostUri, path, uriVariables);
        return getResponse(uri, responseType, httpMethod, body, headers);
    }

    public <T> ResponseEntity<T> request(URI uri, HttpHeaders headers, Class<T> responseType,
                                         HttpMethod httpMethod, Object body) {
        return getResponse(uri, responseType, httpMethod, body, headers);
    }

    private <T> ResponseEntity<T> getResponse(URI uri, Class<T> responseType, HttpMethod httpMethod, Object body,
                                              HttpHeaders headers) {
        HttpEntity<String> entity;
        if (body != null) {
            entity = new HttpEntity<>(Utils.toJson(body), headers);
        } else {
            entity = new HttpEntity<>(headers);
        }

        switch (httpMethod) {
            case GET:
                return restTemplate.exchange(uri, HttpMethod.GET, entity, responseType);
            case PUT:
                return restTemplate.exchange(uri, HttpMethod.PUT, entity, responseType);
            case PATCH:
                return restTemplate.exchange(uri, HttpMethod.PATCH, entity, responseType);
            case DELETE:
                return restTemplate.exchange(uri, HttpMethod.DELETE, entity, responseType);
            case POST:
                return restTemplate.exchange(uri, HttpMethod.POST, entity, responseType);
            default:
                return null;
        }
    }
}
