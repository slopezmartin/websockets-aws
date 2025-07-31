package org.softwaresapiens.websockets.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UpdateReportResponse {
    private String path;
    private ReportStatus status;
}
