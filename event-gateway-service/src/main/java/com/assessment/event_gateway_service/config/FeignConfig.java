package com.assessment.event_gateway_service.config;

import feign.RequestInterceptor;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

@Bean
public RequestInterceptor traceInterceptor(){

return template -> {

template.header(
"X-TRACE-ID",
"event-gateway");

};

}

}