package org.softwaresapiens.websockets.controllers;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.softwaresapiens.websockets.domain.CreateReportRequest;
import org.softwaresapiens.websockets.domain.CreateReportResponse;
import org.softwaresapiens.websockets.domain.Report;
import org.softwaresapiens.websockets.services.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTest {

    @InjectMocks
    private ReportController controllerUnderTest;
    @Mock
    private ReportService mockReportService;

    @Test
    void testCreateReport(){

        CreateReportRequest createReportRequest = new CreateReportRequest();
        createReportRequest.setType("reportType");

        Report reportCreated = new Report(UUID.randomUUID(),createReportRequest.getType());

        when(mockReportService.create(createReportRequest.getType())).thenReturn(reportCreated);
        ResponseEntity<CreateReportResponse> response = controllerUnderTest.createReport(createReportRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
        assertEquals(reportCreated.getId(), response.getBody().getId());
        assertEquals(reportCreated.getType(), response.getBody().getType());

        verify(mockReportService).create(createReportRequest.getType());
        verifyNoMoreInteractions(mockReportService);
    }
}