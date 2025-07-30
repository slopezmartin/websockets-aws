package org.softwaresapiens.websockets.domain;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateReportResponse {
    private UUID id;
    private String type;
}
