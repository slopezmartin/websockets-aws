package org.softwaresapiens.websockets.controllers;

import lombok.RequiredArgsConstructor;
import org.softwaresapiens.websockets.domain.*;
import org.softwaresapiens.websockets.services.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PutMapping("/update/{uuid}")
    public ResponseEntity<UpdateReportResponse> updateReport(@PathVariable("uuid") UUID uuid, @RequestBody UpdateReportRequest updateReportRequest) {
        Report reportUpdated = reportService.update(uuid, updateReportRequest.getPath(), updateReportRequest.getStatus());
        return ResponseEntity.ok(new UpdateReportResponse(reportUpdated.getPath(), reportUpdated.getStatus()));
    }
}
