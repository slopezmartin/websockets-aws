package org.softwaresapiens.websockets.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.softwaresapiens.websockets.domain.*;
import org.softwaresapiens.websockets.services.ReportService;
import org.softwaresapiens.websockets.services.WebSocketNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
@Slf4j
public class ReportController {

    private final ReportService reportService;
    private final WebSocketNotificationService webSocketNotificationService;
    private Map<String, String> reportDb = new HashMap<>();

    @PostMapping("/create")
    public ResponseEntity<CreateReportResponse> createReport(@RequestBody CreateReportRequest createReportRequest) {
        log.info("Creating report request: {}", createReportRequest.toString());
        Report createdReport = reportService.create(createReportRequest.getType());
        reportDb.put(createdReport.getId().toString(), createReportRequest.getConnectionId());
        CreateReportResponse response = new CreateReportResponse(createdReport.getId(), createdReport.getType());
        log.info("Report created with ID: {}, Type: {}", createdReport.getId(), createdReport.getType());
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<UpdateReportResponse> updateReport(@PathVariable("uuid") UUID uuid, @RequestBody UpdateReportRequest updateReportRequest) {
        Report reportUpdated = reportService.update(uuid, updateReportRequest.getPath(), updateReportRequest.getStatus());
        String connectionId = reportDb.get(uuid.toString());
        Map<String,Object> payload = new HashMap<>();
        payload.put("id", reportUpdated.getId());
        payload.put("status", updateReportRequest.getStatus());
        payload.put("path", updateReportRequest.getPath());
        webSocketNotificationService.notifyClient(connectionId, payload);
        reportDb.remove(uuid.toString());
        log.info("Clave borrada de reportDb: {}", uuid.toString());
        return ResponseEntity.ok(new UpdateReportResponse(reportUpdated.getPath(), reportUpdated.getStatus()));
    }
}
