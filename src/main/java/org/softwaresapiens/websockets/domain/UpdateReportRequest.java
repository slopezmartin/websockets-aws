package org.softwaresapiens.websockets.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateReportRequest {
    private String path;
    private ReportStatus status;
}
