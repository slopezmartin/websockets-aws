package org.softwaresapiens.websockets.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.apigatewaymanagementapi.ApiGatewayManagementApiClient;
import java.net.URI;

@Configuration
public class ApiGatewayConfig {

    @Value("${aws.apigateway.websocket.endpoint}")
    private String apiGatewayEndpoint;

    @Bean
    public ApiGatewayManagementApiClient apiGatewayManagementApiClient() {
        return ApiGatewayManagementApiClient.builder()
                .endpointOverride(URI.create(apiGatewayEndpoint))
                .build();
    }
}