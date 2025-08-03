package org.softwaresapiens.websockets.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateReportRequest {
    private String action;
    private String type;
}
