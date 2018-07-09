package com.ooa1769.bs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        restTemplate.setRequestFactory(new HttpComponentsAsyncClientHttpRequestFactory());
        return restTemplate;
    }

    private List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converters.add(converter);
        //converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return converters;
}

}
