package com.travelin.travelinapi.configurations.interceptors;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import reactor.core.publisher.Mono;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/26/2022, Mon
 **/

@Log4j2
@Configuration
public class GraphQLInterceptorConfig implements WebGraphQlInterceptor {
    @NotNull
    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        log.info("Calling GraphQL operation " + request.getOperationName());
        return chain.next(request);
    }

    @NotNull
    @Override
    public WebGraphQlInterceptor andThen(@NotNull WebGraphQlInterceptor nextInterceptor) {
        return WebGraphQlInterceptor.super.andThen(nextInterceptor);
    }
}
