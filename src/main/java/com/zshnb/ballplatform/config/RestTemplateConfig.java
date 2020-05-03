package com.zshnb.ballplatform.config;

import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

@Configuration
public class RestTemplateConfig {
	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory){
		RestTemplate restTemplate = new RestTemplate(factory);
		DefaultUriTemplateHandler handler = new DefaultUriTemplateHandler();
		handler.setBaseUrl("http://localhost:8080");
		restTemplate.setUriTemplateHandler(handler);
		// do nothing when call api occur 400 or other HTTP_STATUS
		restTemplate.setErrorHandler(new ResponseErrorHandler() {
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return false;
			}

			@Override
			public void handleError(ClientHttpResponse response) throws IOException {

			}
		});
		return restTemplate;
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(150000);
		factory.setReadTimeout(50000);
		return factory;
	}
}
