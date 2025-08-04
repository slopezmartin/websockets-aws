package org.softwaresapiens.websockets.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.apigatewaymanagementapi.ApiGatewayManagementApiClient;
import software.amazon.awssdk.services.apigatewaymanagementapi.model.PostToConnectionRequest;
import software.amazon.awssdk.services.apigatewaymanagementapi.model.ApiGatewayManagementApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@Slf4j
public class WebSocketNotificationService {

    private final ApiGatewayManagementApiClient apiGatewayManagementApiClient;
    private final ObjectMapper objectMapper;

    public WebSocketNotificationService(ApiGatewayManagementApiClient apiGatewayManagementApiClient, ObjectMapper objectMapper) {
        this.apiGatewayManagementApiClient = apiGatewayManagementApiClient;
        this.objectMapper = objectMapper;
    }

    public void notifyClient(String connectionId, Map<String, Object> payload) {
        try {
            // Convierte el mapa de datos a una cadena JSON
            String message = objectMapper.writeValueAsString(payload);

            PostToConnectionRequest request = PostToConnectionRequest.builder()
                    .connectionId(connectionId)
                    .data(SdkBytes.fromByteArray(message.getBytes(StandardCharsets.UTF_8)))
                    .build();

            apiGatewayManagementApiClient.postToConnection(request);
            log.info("Mensaje enviado a la conexión: " + connectionId);

        } catch (ApiGatewayManagementApiException e) {
            // Este error puede ocurrir si la conexión ya no está activa.
            log.error("Error al enviar mensaje a la conexión " + connectionId + ": " + e.getMessage());
            // Lógica para manejar la conexión caducada, si es necesario.
        } catch (JsonProcessingException e) {
            log.error("Error al serializar el payload JSON: " + e.getMessage());
        }
    }
}