package org.softwaresapiens.websockets.domain;


import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Report {
    private UUID id;
    private String type;
    private String path;
    private ReportStatus status;
}
