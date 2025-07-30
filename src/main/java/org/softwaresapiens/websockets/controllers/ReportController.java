package org.softwaresapiens.websockets.controllers;

import lombok.RequiredArgsConstructor;
import org.softwaresapiens.websockets.domain.CreateReportRequest;
import org.softwaresapiens.websockets.domain.CreateReportResponse;
import org.softwaresapiens.websockets.domain.Report;
import org.softwaresapiens.websockets.services.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/create")
    public ResponseEntity<CreateReportResponse> createReport(@RequestBody CreateReportRequest createReportRequest) {
        Report createdReport = reportService.create(createReportRequest.getType());
        return new ResponseEntity(new CreateReportResponse(createdReport.getId(), createdReport.getType()), HttpStatus.CREATED);
    }
}
