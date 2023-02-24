package com.coocon.admin.common.httpclient;

import com.coocon.admin.product.entity.Product;
import com.coocon.admin.product.entity.ProductApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Component
@RequiredArgsConstructor
public class productWebClient {

    private final HttpClient httpClient;
    private final ExchangeStrategies exchangeStrategies;

    public WebClient getWebClient(Product product){
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(product.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(exchangeStrategies)
                .build();
    }

    public ResponseEntity<?> sendWebClientApi(Product product, ProductApi productApi, Object requestObj){
        WebClient webClient = getWebClient(product);

        if(productApi.getMethod().equals("POST")){
            return sendPostWebClientApi(webClient,productApi,requestObj) ;
        }
        else if(productApi.getMethod().equals("GET")){
            return sendGetWebClientApi(webClient,productApi,requestObj)   ;
        }
        else {
            throw new UnsupportedOperationException("지원하지 않는 METHOD 입니다.");
        }
    }

    public ResponseEntity<?> sendPostWebClientApi(WebClient webClient, ProductApi productApi,Object requestObj){
        return webClient.post().uri(productApi.getUrl()).bodyValue(requestObj).retrieve()
                .toEntity(ResponseEntity.class).block();
    }

    public ResponseEntity<?> sendGetWebClientApi(WebClient webClient, ProductApi productApi,Object requestObj){
        return webClient.get().uri(productApi.getUrl()).retrieve()
                .toEntity(ResponseEntity.class).block();
    }
}
