package com.ooa1769.bs.config;

import com.ooa1769.bs.book.infra.respository.rest.KakaoApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@PropertySource({ "classpath:kakao.properties" })
public class AppConfig {

    @Autowired
    private Environment env;

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

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        return pspc;
    }

    @Bean
    public KakaoApiProperties kakaoProperties() {
        KakaoApiProperties kakaoApiProperties = new KakaoApiProperties();
        kakaoApiProperties.setKey(env.getProperty("kakao.api.key"));
        kakaoApiProperties.setUrl(env.getProperty("kakao.api.url"));
        return kakaoApiProperties;
    }
}
