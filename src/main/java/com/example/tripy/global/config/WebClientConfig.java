package com.example.tripy.global.config;

import com.example.tripy.domain.member.MemberRepository;
import com.example.tripy.global.security.CurrentUserArgumentResolver;
import com.example.tripy.global.security.JwtTokenProvider;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import reactor.netty.http.client.HttpClient;
import java.time.Duration;
import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ReactorResourceFactory;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig implements WebMvcConfigurer {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    @Bean
    public ReactorResourceFactory resourceFactory() {
        ReactorResourceFactory factory = new ReactorResourceFactory();
        factory.setUseGlobalResources(false);
        return factory;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CurrentUserArgumentResolver(jwtTokenProvider, memberRepository));
    }

    @Bean
    public WebClient webClient() {
        Function<HttpClient, HttpClient> mapper = client -> client
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
            .doOnConnected(connection ->
                connection.addHandlerLast(new ReadTimeoutHandler(10))
                    .addHandlerLast(new WriteTimeoutHandler(10)))
            .responseTimeout(Duration.ofSeconds(1));

        ClientHttpConnector connector =
            new ReactorClientHttpConnector(resourceFactory(), mapper);
        return WebClient.builder().clientConnector(connector).build();
    }


}
